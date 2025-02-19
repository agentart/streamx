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

package com.streamxhub.streamx.plugin.profiling;

import com.streamxhub.streamx.plugin.profiling.util.AgentLogger;

import java.lang.instrument.Instrumentation;

/**
 * @author benjobs
 */
public final class Agent {

    private static final AgentLogger LOGGER = AgentLogger.getLogger(Agent.class.getName());

    private static AgentImpl agentImpl = new AgentImpl();

    private Agent() {
    }

    public static void agentmain(final String args, final Instrumentation instrumentation) {
        premain(args, instrumentation);
    }

    public static void premain(final String args, final Instrumentation instrumentation) {
        LOGGER.info("Java Agent " + AgentImpl.VERSION + " premain args: " + args);
        Arguments arguments = Arguments.parseArgs(args);
        arguments.runConfigProvider();
        agentImpl.run(arguments, instrumentation, null);
    }
}
