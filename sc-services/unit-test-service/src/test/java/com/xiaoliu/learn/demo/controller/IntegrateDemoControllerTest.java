package com.xiaoliu.learn.demo.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @description: 集成测试
 * @author: FuBiaoLiu
 * @date: 2019/7/26
 */
public class IntegrateDemoControllerTest extends BaseControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrateDemoControllerTest.class);
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldSuccessWhenGetDictOptionByName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/demo/getDictOptionByName")
                        .param("name", "评估模型")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        LOGGER.info("Mock结果是:{}", content);
        Assert.assertEquals(200, status);
        Assert.assertTrue(content.contains("雷击"));
    }
}