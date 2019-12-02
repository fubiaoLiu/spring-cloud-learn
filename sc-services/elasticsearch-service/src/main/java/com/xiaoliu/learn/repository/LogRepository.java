package com.xiaoliu.learn.repository;

import com.xiaoliu.learn.model.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @description:
 * @param: -
 * Log:实体类
 * Long:Log实体类中主键的数据类型
 * @author: FuBiaoLiu
 * @date: 2019/11/29
 */
public interface LogRepository extends ElasticsearchRepository<Log, Long> {
    /**
     * 根据日志等级查询
     *
     * @param level 日志等级
     * @return
     */
    List<Log> findByLevel(String level);
}
