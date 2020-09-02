package com.xiaoliu.learn.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 短信发送API
 * @author: liufb
 * @create: 2020/7/31 11:24
 **/
@RequestMapping("/message")
public interface SendMessageServiceApi {
    /**
     * 发送短信
     *
     * @param phone 手机号码
     */
    @PostMapping("/send")
    void send(@RequestParam("phone") String phone);
}
