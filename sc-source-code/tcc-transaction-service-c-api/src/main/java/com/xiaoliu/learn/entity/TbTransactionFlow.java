package com.xiaoliu.learn.entity;

import lombok.Data;

/**
 * @description: 交易流水
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Data
public class TbTransactionFlow {
    private Long id;
    private String fromAcctId;
    private String toAcctId;
    private Double amount;
    private Integer status;
}
