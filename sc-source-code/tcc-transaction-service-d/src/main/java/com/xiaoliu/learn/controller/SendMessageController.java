package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.constant.MessageStatus;
import com.xiaoliu.learn.service.SendMessageService;
import com.xiaoliu.learn.service.SendMessageServiceApi;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableCancel;
import org.bytesoft.compensable.CompensableConfirm;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 账户Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@Compensable(interfaceClass = SendMessageServiceApi.class, simplified = true)
@RestController
public class SendMessageController implements SendMessageServiceApi, CompensableContextAware {
    private CompensableContext compensableContext;

    @Autowired
    private SendMessageService sendMessageService;

    @Override
    @Transactional
    public void send(String phone) {
        Long messageId = sendMessageService.send(phone);
        compensableContext.setVariable("messageId", messageId);
    }

    @CompensableConfirm
    @Transactional
    public void confirmSend(String phone) {
        Long messageId = (Long) compensableContext.getVariable("messageId");
        sendMessageService.updateStatus(messageId, MessageStatus.SUCCESS);
//        throw new RuntimeException();
    }

    @CompensableCancel
    @Transactional
    public void cancelSend(String phone) {
        Long messageId = (Long) compensableContext.getVariable("messageId");
        sendMessageService.updateStatus(messageId, MessageStatus.FAILURE);
    }

    @Override
    public void setCompensableContext(CompensableContext aware) {
        this.compensableContext = aware;
    }

}
