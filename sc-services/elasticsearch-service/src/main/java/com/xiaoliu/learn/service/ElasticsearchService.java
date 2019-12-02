package com.xiaoliu.learn.service;

/**
 * @description: Elasticsearch service
 * @author: FuBiaoLiu
 * @date: 2019/11/28
 */
public interface ElasticsearchService {
    /**
     * 根据类信息创建索引
     *
     * @param clazz
     */
    void createIndex(Class clazz);

    /**
     * 根据类信息创建映射
     *
     * @param clazz
     */
    void createMapping(Class clazz);

    /**
     * 删除索引
     *
     * @param clazz
     */
    void deleteIndex(Class clazz);
}
