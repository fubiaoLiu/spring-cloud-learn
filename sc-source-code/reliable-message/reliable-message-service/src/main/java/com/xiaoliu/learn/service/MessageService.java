package com.xiaoliu.learn.service;

/**
 * @description: 消息Service组件接口
 * @author: liufb
 * @create: 2020/8/24 10:58
 **/
public interface MessageService {
    Long prepareMessage(String message);

    void confirmMessage(Long messageId);

    void removeMessage(Long messageId);

    void finishMessage(Long messageId);
}
