package com.xiaoliu.learn.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 交易流水API
 * @author: liufb
 * @create: 2020/7/31 11:24
 **/
@RequestMapping("/transactionFlow")
public interface TransactionFlowServiceApi {
    /**
     * 交易流水
     *
     * @param fromAcctId 转出用户ID
     * @param toAcctId   转入用户ID
     * @param amount     金额
     */
    @PostMapping("/")
    void save(@RequestParam("fromAcctId") String fromAcctId,
              @RequestParam("toAcctId") String toAcctId,
              @RequestParam("amount") double amount);
}
