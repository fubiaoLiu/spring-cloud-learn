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
    public String provider() throws Exception {
        System.out.println("provider2");
        /*try {
            // 模拟超时
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "provider2";

        // 模拟异常
//        throw new Exception("系统异常");
    }
}
