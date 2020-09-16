package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.constant.MessageStatus;
import com.xiaoliu.learn.service.SendMessageService;
import com.xiaoliu.learn.service.SendMessageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 账户Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@RequestMapping("/message/notcc")
@RestController
public class SendMessageNoTCCController implements SendMessageServiceApi{
    @Autowired
    private SendMessageService sendMessageService;

    @Override
    public void send(String phone) {
        Long messageId = sendMessageService.send(phone);
        sendMessageService.updateStatus(messageId, MessageStatus.SUCCESS);
    }
}
