package com.xiaoliu.learn.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 账户API
 * @author: liufb
 * @create: 2020/7/31 11:24
 **/
@RequestMapping("/account")
public interface AccountServiceApi {
    /**
     * 转入
     *
     * @param acctId 用户ID
     * @param amount 转入金额
     */
    @PutMapping("/transferIn")
    void transferIn(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * 转出
     *
     * @param acctId 用户ID
     * @param amount 转出金额
     */
    @PostMapping("/transferOut")
    void transferOut(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);
}
