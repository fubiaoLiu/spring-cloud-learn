package com.xiaoliu.learn.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 可靠消息服务API接口
 * @author: liufb
 * @create: 2020/9/8 16:27
 **/
@RequestMapping("/reliableMessage")
public interface ReliableMessageApi {
    /**
     * 发送待确认消息
     *
     * @param message 消息内容
     * @return 消息ID
     */
    @PostMapping(value = "/prepare")
    Long prepareMessage(@RequestParam("message") String message);

    /**
     * 确认发送消息
     *
     * @param messageId 消息ID
     */
    @PutMapping(value = "/confirm")
    void confirmMessage(@RequestParam("messageId") Long messageId);

    /**
     * 删除消息
     *
     * @param messageId 消息ID
     */
    @PutMapping(value = "/remove")
    void removeMessage(@RequestParam("messageId") Long messageId);

    /**
     * 完成消息
     *
     * @param messageId 消息ID
     */
    @PutMapping(value = "/finish")
    void finishMessage(@RequestParam("messageId") Long messageId);
}
