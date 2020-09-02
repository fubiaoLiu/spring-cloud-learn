package com.xiaoliu.learn.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: 消息发送记录
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Data
public class TbSendMessage {
    private Long id;
    private String phone;
    private Integer status;
    private Date create_time;
}
