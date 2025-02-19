/*
 * Copyright 2019 The StreamX Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamxhub.streamx.flink.repl.interpreter

import com.streamxhub.streamx.common.util.{ClassLoaderUtils, Logger}

import java.util.Properties

/**
 * Interpreter for flink scala. It delegates all the function to FlinkScalaInterpreter.
 */
class FlinkInterpreter(properties: Properties) extends Logger {

  private var interpreter: FlinkScalaInterpreter = _

  private def checkScalaVersion(): Unit = {
    val scalaVersion = scala.util.Properties.versionString
    logInfo("Using Scala: " + scalaVersion)
    if (!scalaVersion.contains("version 2.11")) {
      // scalastyle:off throwerror
      throw new ExceptionInInitializerError(s"Unsupported scala version: $scalaVersion Only scala 2.11 is supported")
      // scalastyle:on throwerror
    }
  }

  @throws[Exception] def open(flinkHome: String): Unit = {
    checkScalaVersion()
    this.interpreter = new FlinkScalaInterpreter(properties)
    this.interpreter.open(flinkHome)
  }

  @throws[Exception] def close(): Unit = if (this.interpreter != null) this.interpreter.close()

  @throws[Exception] def interpret(code: String, out: InterpreterOutput): InterpreterResult = {
    // set ClassLoader of current Thread to be the ClassLoader of Flink scala-shell,
    // otherwise codegen will fail to find classes defined in scala-shell
    ClassLoaderUtils.runAsClassLoader(getFlinkScalaShellLoader, () => {
      interpreter.interpret(code, out)
    })
  }

  private[flink] def getStreamExecutionEnvironment = this.interpreter.getStreamExecutionEnvironment()

  private[flink] def getDefaultParallelism: Int = this.interpreter.defaultParallelism

  def getFlinkScalaShellLoader: ClassLoader = interpreter.getFlinkScalaShellLoader

  private[flink] def getFlinkConfiguration = this.interpreter.getConfiguration

}
