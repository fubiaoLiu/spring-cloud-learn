package com.xiaoliu.learn.demo.mapper;

import com.xiaoliu.learn.demo.model.Dictionary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DemoMapperTest {
    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void shouldSuccessWhenGetDictOptionByName() {
        List<Dictionary> list = demoMapper.getDictOptionByName("评估模型");
        Assert.assertSame(12, list.size());
    }

    @Test
    public void shouldSuccessWhenInsertDict() {
        Dictionary dictionary = new Dictionary();
        dictionary.setCategory("UNIT_TEST");
        dictionary.setCategoryName("单元测试");
        dictionary.setCodeValue("01");
        dictionary.setCodeText("01");
        demoMapper.insert(dictionary);
    }
}