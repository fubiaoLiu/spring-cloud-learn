package com.xiaoliu.learn.demo.service;

import com.xiaoliu.learn.demo.model.Dictionary;

import java.util.List;

/**
 * @description: Demo Service
 * @author: FuBiaoLiu
 * @date: 2019/6/28
 */
public interface DemoService {
    List<Dictionary> getDictOptionByName(String name);
}
