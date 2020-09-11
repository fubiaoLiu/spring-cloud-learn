package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.service.MessageService;
import com.xiaoliu.learn.service.ReliableMessageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 可靠消息服务Controller
 * @author: liufb
 * @create: 2020/9/8 16:37
 **/
@RestController
public class ReliableMessageController implements ReliableMessageApi {
    @Autowired
    private MessageService messageService;

    @Override
    public Long prepareMessage(String message) {
        return messageService.prepareMessage(message);
    }

    @Override
    public void confirmMessage(Long messageId) {
        messageService.confirmMessage(messageId);
    }

    @Override
    public void removeMessage(Long messageId) {
        messageService.removeMessage(messageId);
    }

    @Override
    public void finishMessage(Long messageId) {
        messageService.finishMessage(messageId);
    }
}
