package com.xiaoliu.learn.demo.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @description: 集成测试基础类
 * @author: FuBiaoLiu
 * @date: 2019/7/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseControllerTest {
    @Autowired
    protected WebApplicationContext wac;
}
