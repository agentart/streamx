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

package com.streamxhub.streamx.console.core.mapper;

import com.streamxhub.streamx.console.core.entity.Setting;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author benjobs
 */
public interface SettingMapper extends BaseMapper<Setting> {

    @Select("select * from t_setting where setting_key=#{key}")
    Setting get(@Param("key") String key);

    @Update("update t_setting set setting_value = #{setting.settingValue} where setting_key = #{setting.settingKey}")
    void updateByKey(@Param("setting") Setting setting);
}
