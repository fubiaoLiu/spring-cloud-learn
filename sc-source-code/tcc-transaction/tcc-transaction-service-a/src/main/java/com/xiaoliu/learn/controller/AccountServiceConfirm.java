package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.service.AccountService;
import com.xiaoliu.learn.service.AccountServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 账户Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@RequestMapping("/account/confirm")
@Service("accountServiceConfirm")
public class AccountServiceConfirm implements AccountServiceApi {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public void transferIn(String acctId, double amount) {
        accountService.confirmTransferIn(acctId, amount);
        // throw new RuntimeException();
    }

    @Override
    @Transactional
    public void transferOut(String acctId, double amount) {
        accountService.transferOut(acctId, amount);
    }
}
