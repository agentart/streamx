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

package com.streamxhub.streamx.console.base.exception;

/**
 * 报警异常
 *
 * @author weijinglun
 * @date 2022.07.21
 */
public class AlertException extends ApiException {
    public AlertException(String message) {
        super(message);
    }

    public AlertException(String message, Throwable cause) {
        super(message, cause);
    }
}
