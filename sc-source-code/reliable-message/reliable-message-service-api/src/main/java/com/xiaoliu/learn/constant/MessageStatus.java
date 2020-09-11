package com.xiaoliu.learn.constant;

/**
 * @description: 消息状态常量
 * @author: liufb
 * @create: 2020/9/8 16:49
 **/
public class MessageStatus {
    private MessageStatus() {
    }

    public static final Integer PREPARED = 1;
    public static final Integer CONFIRMED = 2;
    public static final Integer FINISHED = 3;
    public static final Integer REMOVED = 4;
}
