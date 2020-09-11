package com.xiaoliu.learn.service.impl;

import com.xiaoliu.learn.constant.MessageStatus;
import com.xiaoliu.learn.entity.Message;
import com.xiaoliu.learn.mapper.MessageMapper;
import com.xiaoliu.learn.rabbitmq.MessageProducer;
import com.xiaoliu.learn.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @description: 消息Service组件
 * @author: liufb
 * @create: 2020/8/24 11:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Long prepareMessage(String message) {
        Message messageObj = new Message();
        messageObj.setStatus(MessageStatus.PREPARED);
        messageObj.setContent(message);
        messageMapper.insert(messageObj);

        return messageObj.getId();
    }

    @Override
    public void confirmMessage(Long messageId) {
        Message message = new Message();
        message.setStatus(MessageStatus.CONFIRMED);
        message.setId(messageId);
        message.setConfirmedTime(new Date());
        messageMapper.confirm(message);

        // 投递到MQ
        messageProducer.send(message.getContent());
    }

    @Override
    public void removeMessage(Long messageId) {
        Message message = new Message();
        message.setStatus(MessageStatus.REMOVED);
        message.setId(messageId);
        message.setRemovedTime(new Date());
        messageMapper.remove(message);
    }

    @Override
    public void finishMessage(Long messageId) {
        Message message = new Message();
        message.setStatus(MessageStatus.FINISHED);
        message.setId(messageId);
        message.setFinishedTime(new Date());
        messageMapper.finish(message);
    }
}
