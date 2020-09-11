package com.xiaoliu.learn.service.impl;

import com.xiaoliu.learn.mapper.AccountMapper;
import com.xiaoliu.learn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 账户Service组件
 * @author: liufb
 * @create: 2020/8/24 11:00
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void frozenAmount(String acctId, double amount) {
        accountMapper.frozenAmount(acctId, amount);
    }

    @Override
    public void unfrozenAmount(String acctId, double amount) {
        accountMapper.unfrozenAmount(acctId, amount);
    }

    @Override
    public void transferOut(String acctId, double amount) {
        accountMapper.transferOut(acctId, amount);
    }

    @Override
    public void tryTransferIn(String acctId, double amount) {
        accountMapper.tryTransferIn(acctId, amount);
    }

    @Override
    public void confirmTransferIn(String acctId, double amount) {
        accountMapper.confirmTransferIn(acctId, amount);
    }

    @Override
    public void cancelTransferIn(String acctId, double amount) {
        accountMapper.cancelTransferIn(acctId, amount);
    }
}
