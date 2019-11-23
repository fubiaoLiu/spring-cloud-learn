package com.xiaoliu.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: provider
 * @author: FuBiaoLiu
 * @date: 2018/12/12
 */
@RestController
public class ProviderController {

    @GetMapping("/provider")
    public String provider() {
        System.out.println("provider1");
        return "provider1";
    }
}
