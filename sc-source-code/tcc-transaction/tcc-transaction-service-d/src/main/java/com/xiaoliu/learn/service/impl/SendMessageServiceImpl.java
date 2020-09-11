package com.xiaoliu.learn.service.impl;

import com.xiaoliu.learn.constant.MessageStatus;
import com.xiaoliu.learn.entity.TbSendMessage;
import com.xiaoliu.learn.mapper.SendMessageMapper;
import com.xiaoliu.learn.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 账户Service组件
 * @author: liufb
 * @create: 2020/8/24 11:00
 **/
@Service
public class SendMessageServiceImpl implements SendMessageService {
    @Autowired
    private SendMessageMapper sendMessageMapper;

    @Override
    public Long send(String phone) {
        TbSendMessage tbSendMessage = new TbSendMessage();
        tbSendMessage.setPhone(phone);
        tbSendMessage.setStatus(MessageStatus.SENDING);
        sendMessageMapper.insert(tbSendMessage);
        return tbSendMessage.getId();
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        sendMessageMapper.updateStatus(id, status);
    }
}
