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

package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.HackPullImageResultCallback;
import com.github.dockerjava.api.listener.PullImageCallbackListener;
import com.github.dockerjava.api.model.AuthConfig;

/**
 * Listenable PullImageCmdImpl
 *
 * @author Al-assad
 */
public class HackPullImageCmd extends PullImageCmdImpl {

    public HackPullImageCmd(Exec exec, AuthConfig authConfig, String repository) {
        super(exec, authConfig, repository);
    }

    public HackPullImageResultCallback start(PullImageCallbackListener listener) {
        return exec(new HackPullImageResultCallback(listener));
    }

}
