package com.xiaoliu.transaction.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: 用户类
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Date gmtCreate;
    private Date gmtModified;
}
