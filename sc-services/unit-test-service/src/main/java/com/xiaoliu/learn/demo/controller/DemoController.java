package com.xiaoliu.learn.demo.controller;

import com.xiaoliu.learn.demo.model.Dictionary;
import com.xiaoliu.learn.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: DemoController
 * @author: FuBiaoLiu
 * @date: 2019/6/28
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping("/getDictOptionByName")
    @ResponseBody
    public List<Dictionary> getDictOptionByName(@RequestParam("name") String name) {
        return demoService.getDictOptionByName(name);
    }
}
