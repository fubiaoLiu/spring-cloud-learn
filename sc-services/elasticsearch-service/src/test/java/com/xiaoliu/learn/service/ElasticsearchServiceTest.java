package com.xiaoliu.learn.service;

import com.xiaoliu.learn.model.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchServiceTest {
    @Autowired
    private ElasticsearchService elasticsearchService;

    @Test
    public void shouldSuccessWhenCreateIndex() {
        elasticsearchService.createIndex(Log.class);
    }

    @Test
    public void shouldSuccessWhenCreateMapping() {
        elasticsearchService.createMapping(Log.class);
    }

    @Test
    public void shouldSuccessWhenDeleteIndex() {
        elasticsearchService.deleteIndex(Log.class);
    }
}
