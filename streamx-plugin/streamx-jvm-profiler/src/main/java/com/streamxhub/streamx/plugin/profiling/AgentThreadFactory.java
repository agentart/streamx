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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author benjobs
 */
public class AgentThreadFactory implements ThreadFactory {
    public static final String NAME_PREFIX = "uber_java_agent";

    private static final ThreadFactory DEFAULT_THREAD_FACTORY = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = DEFAULT_THREAD_FACTORY.newThread(r);
        if (thread != null) {
            thread.setDaemon(true);
            thread.setName(String.format("%s-%s", NAME_PREFIX, thread.getName()));
        }

        return thread;
    }
}
