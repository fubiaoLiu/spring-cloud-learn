package com.xiaoliu.learn.constant;


/**
 * @description: 交易流水状态
 * @author: liufb
 * @create: 2020/8/24 17:37
 **/
public class MessageStatus {
    private MessageStatus() {
    }

    /**
     * 发送中
     */
    public static final int SENDING = 0;

    /**
     * 发送成功
     */
    public static final int SUCCESS = 1;

    /**
     * 发送失败
     */
    public static final int FAILURE = 2;
}
