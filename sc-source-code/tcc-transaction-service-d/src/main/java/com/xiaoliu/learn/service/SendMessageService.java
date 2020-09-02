package com.xiaoliu.learn.service;

/**
 * @description: 消息发送接口
 * @author: liufb
 * @create: 2020/8/24 10:58
 **/
public interface SendMessageService {
    /**
     * 保存交易流水
     *
     * @param phone 手机号码
     * @return ID
     */
    Long send(String phone);

    /**
     * 更新状态
     *
     * @param id     ID
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);
}
