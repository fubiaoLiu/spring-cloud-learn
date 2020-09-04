package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.client.ServiceDClient;
import com.xiaoliu.learn.service.AccountService;
import com.xiaoliu.learn.service.AccountServiceApi;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 账户Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@Compensable(
        interfaceClass = AccountServiceApi.class,
        confirmableKey = "accountServiceConfirm",
        cancellableKey = "accountServiceCancel")
@RestController
public class AccountController implements AccountServiceApi {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ServiceDClient serviceDClient;

    @Override
    @Transactional
    public void transferIn(String acctId, double amount) {
        accountService.tryTransferIn(acctId, amount);
        serviceDClient.send("16666666666");
    }

    @Override
    @Transactional
    public void transferOut(String acctId, double amount) {
        accountService.frozenAmount(acctId, amount);
        serviceDClient.send("16666666666");
    }
}
