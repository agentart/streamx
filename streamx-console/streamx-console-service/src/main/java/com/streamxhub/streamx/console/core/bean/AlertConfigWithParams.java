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

package com.streamxhub.streamx.console.core.bean;

import com.streamxhub.streamx.console.base.util.JacksonUtils;
import com.streamxhub.streamx.console.core.entity.AlertConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author weijinglun
 * @date 2022.01.14
 */
@Data
@Slf4j
public class AlertConfigWithParams implements Serializable {
    private Long id;

    private Long userId;

    private String alertName;

    /**
     * 报警类型
     */
    private Integer alertType;

    /**
     * 邮件报警配置信息
     */
    private AlertEmailParams emailParams;

    /**
     * 钉钉报警配置信息
     */
    private AlertDingTalkParams dingTalkParams;

    /**
     * 企微报警配置信息
     */
    private AlertWeComParams weComParams;

    /**
     * 报警http回调配置信息
     */
    private AlertHttpCallbackParams httpCallbackParams;

    /**
     * 飞书报警配置信息
     */
    private AlertLarkParams larkParams;

    public static AlertConfigWithParams of(AlertConfig config) {
        if (config == null) {
            return null;
        }
        AlertConfigWithParams params = new AlertConfigWithParams();
        BeanUtils.copyProperties(config, params, "emailParams", "dingTalkParams", "weComParams", "httpCallbackParams", "larkParams");
        try {
            if (StringUtils.isNotBlank(config.getEmailParams())) {
                params.setEmailParams(JacksonUtils.read(config.getEmailParams(), AlertEmailParams.class));
            }
            if (StringUtils.isNotBlank(config.getDingTalkParams())) {
                params.setDingTalkParams(JacksonUtils.read(config.getDingTalkParams(), AlertDingTalkParams.class));
            }
            if (StringUtils.isNotBlank(config.getWeComParams())) {
                params.setWeComParams(JacksonUtils.read(config.getWeComParams(), AlertWeComParams.class));
            }
            if (StringUtils.isNotBlank(config.getHttpCallbackParams())) {
                params.setHttpCallbackParams(JacksonUtils.read(config.getHttpCallbackParams(), AlertHttpCallbackParams.class));
            }
            if (StringUtils.isNotBlank(config.getLarkParams())) {
                params.setLarkParams(JacksonUtils.read(config.getLarkParams(), AlertLarkParams.class));
            }
        } catch (JsonProcessingException e) {
            log.error("Json read failed", e);
        }
        return params;
    }
}
