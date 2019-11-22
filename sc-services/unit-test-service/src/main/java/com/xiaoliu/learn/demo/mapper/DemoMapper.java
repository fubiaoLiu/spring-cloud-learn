package com.xiaoliu.learn.demo.mapper;

import com.xiaoliu.learn.demo.model.Dictionary;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description: Demo Mapper
 * @author: FuBiaoLiu
 * @date: 2019/6/28
 */
public interface DemoMapper {
    List<Dictionary> getDictOptionByName(@RequestParam("categoryName") String categoryName);

    void insert(Dictionary dictionary);
}
