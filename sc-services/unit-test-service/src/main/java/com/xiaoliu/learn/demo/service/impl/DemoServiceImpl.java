package com.xiaoliu.learn.demo.service.impl;

import com.xiaoliu.learn.demo.mapper.DemoMapper;
import com.xiaoliu.learn.demo.model.Dictionary;
import com.xiaoliu.learn.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: Demo Service
 * @author: FuBiaoLiu
 * @date: 2019/6/28
 */
@Service
public class DemoServiceImpl implements DemoService {
    private final DemoMapper demoMapper;

    @Autowired
    public DemoServiceImpl(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    @Override
    public List<Dictionary> getDictOptionByName(String name) {
        return demoMapper.getDictOptionByName(name);
    }
}
