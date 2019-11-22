package com.xiaoliu.learn.demo.service;

import com.xiaoliu.learn.demo.mapper.DemoMapper;
import com.xiaoliu.learn.demo.model.Dictionary;
import com.xiaoliu.learn.demo.service.impl.DemoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DemoServiceTest {

    @InjectMocks
    private DemoServiceImpl demoService;
    @Mock
    private DemoMapper demoMapper;

    @Before
    public void setUp() {
        // 准备桩数据
        List<Dictionary> entryList = new ArrayList<>();
        Dictionary dictionary1 = new Dictionary();
        dictionary1.setId(1L);
        Dictionary dictionary2 = new Dictionary();
        dictionary2.setId(2L);
        entryList.add(dictionary1);
        entryList.add(dictionary2);

        Mockito.when(demoMapper.getDictOptionByName("评估模型"))
                .thenReturn(entryList);
        System.out.println("Before run success!");
    }

    @Test
    public void shouldSuccessWhenGetDictOptionByName() {
        List<Dictionary> list = demoService.getDictOptionByName("评估模型");
        Assert.assertSame(1L, list.get(0).getId());
        Assert.assertSame(2L, list.get(1).getId());
        Assert.assertSame("列表记录数错误", 2, list.size());
    }

}

