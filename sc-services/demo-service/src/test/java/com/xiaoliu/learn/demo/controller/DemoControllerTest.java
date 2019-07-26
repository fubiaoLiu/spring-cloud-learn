package com.xiaoliu.learn.demo.controller;

import com.xiaoliu.learn.demo.model.Dictionary;
import com.xiaoliu.learn.demo.service.DemoService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

//@Transactional
@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@WebMvcTest(DemoController.class)
//@AutoConfigureMockMvc
public class DemoControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoControllerTest.class);
    //    @Autowired
    //    private WebApplicationContext wac;
//    @Autowired
    private MockMvc mockMvc;
    @Mock
    private DemoService demoService;
    @InjectMocks
    private DemoController demoController;

    @Before
    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // -------------------------------

        // 准备桩数据
        List<Dictionary> entryList = new ArrayList<>();
        Dictionary dictionary1 = new Dictionary();
        dictionary1.setId(1L);
        dictionary1.setCodeText("雷击");
        Dictionary dictionary2 = new Dictionary();
        dictionary2.setId(2L);
        dictionary1.setCodeText("机械外破");
        entryList.add(dictionary1);
        entryList.add(dictionary2);

        Mockito.when(demoService.getDictOptionByName("评估模型"))
                .thenReturn(entryList);
        // -------------------------------
        // 构造MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldSuccessWhenGetDictOptionByName() throws Exception {
        /*mockMvc.perform(
                MockMvcRequestBuilders.post("/demo/getDictOptionByName")
                        .param("name", "评估模型")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();*/
        mockMvc.perform(MockMvcRequestBuilders.post("/demo/getDictOptionByName")
                .param("name", "评估模型")
                .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("机械外破")));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/demo/getDictOptionByName")
                .param("name", "评估模型")
                .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultStr = "[{\"id\":1,\"category\":null,\"categoryName\":null,\"codeValue\":null,\"codeText\":\"机械外破\",\"status\":null,\"orderNum\":null},{\"id\":2,\"category\":null,\"categoryName\":null,\"codeValue\":null,\"codeText\":null,\"status\":null,\"orderNum\":null}]";
        LOGGER.info("Mock结果是:{}", result.getResponse().getContentAsString());
//        Assert.assertTrue(result.getResponse().getContentAsString().contains("机械外破"));
        Assert.assertEquals(resultStr, result.getResponse().getContentAsString());
    }
}