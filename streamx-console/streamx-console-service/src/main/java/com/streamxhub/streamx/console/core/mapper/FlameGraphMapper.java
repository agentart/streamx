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

import com.streamxhub.streamx.console.core.entity.FlameGraph;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author benjobs
 */
public interface FlameGraphMapper extends BaseMapper<FlameGraph> {

    /**
     * @param appId
     * @param start
     * @param end
     * @return
     */
    @Select("select * from t_flame_graph where app_id=#{appId} and timeline between #{start} and #{end} order by timeline asc")
    List<FlameGraph> getFlameGraph(@Param("appId") Long appId, @Param("start") Date start, @Param("end") Date end);

    @Delete("delete from t_flame_graph where timeline < #{end}")
    void clean(Date end);
}
