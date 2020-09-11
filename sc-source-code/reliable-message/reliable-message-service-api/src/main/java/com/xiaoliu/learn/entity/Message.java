package com.xiaoliu.learn.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: 消息对象
 * @author: liufb
 * @create: 2020/9/8 16:45
 **/
@Data
public class Message {
    private Long id;
    private String content;
    private Integer status;
    private Date createdTime;
    private Date removedTime;
    private Date confirmedTime;
    private Date finishedTime;
}
