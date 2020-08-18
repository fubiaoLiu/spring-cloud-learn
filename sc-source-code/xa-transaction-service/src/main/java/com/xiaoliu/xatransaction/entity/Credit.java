package com.xiaoliu.xatransaction.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: 积分类
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Data
public class Credit {
    private Long id;
    private Long userAccountId;
    private Double point;
    private Date gmtCreate;
    private Date gmtModified;
}
