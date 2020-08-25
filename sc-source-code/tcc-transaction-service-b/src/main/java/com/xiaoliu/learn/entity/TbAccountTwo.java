package com.xiaoliu.learn.entity;

import lombok.Data;

/**
 * @description: 用户账号余额
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Data
public class TbAccountTwo {
    private String acctId;
    private Double amount;
    private Double frozen;
}
