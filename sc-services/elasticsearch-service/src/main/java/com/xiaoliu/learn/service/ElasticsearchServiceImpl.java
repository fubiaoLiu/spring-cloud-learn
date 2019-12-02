package com.xiaoliu.learn.service;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @description: Elasticsearch service
 * @author: FuBiaoLiu
 * @date: 2019/11/28
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private final ElasticsearchTemplate elasticsearchTemplate;

    public ElasticsearchServiceImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * 根据类信息创建索引
     *
     * @param clazz
     */
    @Override
    public void createIndex(Class clazz) {
        elasticsearchTemplate.createIndex(clazz);
    }

    /**
     * 根据类信息创建映射
     *
     * @param clazz
     */
    @Override
    public void createMapping(Class clazz) {
        elasticsearchTemplate.putMapping(clazz);
    }

    /**
     * 删除索引
     *
     * @param clazz
     */
    @Override
    public void deleteIndex(Class clazz) {
        elasticsearchTemplate.deleteIndex(clazz);
    }
}
