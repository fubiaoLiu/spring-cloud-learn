package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 转账Confirm接口
 * @author: liufb
 * @create: 2020/8/24 13:29
 **/
@Service("transferServiceConfirm")
public class TransferServiceConfirm implements TransferService {
    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public void transferIn(String aAcctId, String bAcctId, double amount) {
        accountService.confirmTransferIn(bAcctId, amount);
        // throw new RuntimeException();
    }

    @Override
    @Transactional
    public void transferOut(String bAcctId, String aAcctId, double amount) {
        accountService.transferOut(bAcctId, amount);
        // throw new RuntimeException();
    }
}
