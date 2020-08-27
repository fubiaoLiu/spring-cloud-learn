package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.client.ServiceAClient;
import com.xiaoliu.learn.client.ServiceCClient;
import com.xiaoliu.learn.service.AccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 转账Controller
 * @author: liufb
 * @create: 2020/8/24 13:19
 **/
@Compensable(
        interfaceClass = TransferService.class,
        confirmableKey = "transferServiceConfirm",
        cancellableKey = "transferServiceCancel")
@RequestMapping("/transfer")
@RestController
public class TransferController implements TransferService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ServiceAClient serviceAClient;
    @Autowired
    private ServiceCClient serviceCClient;

    @PutMapping("/in")
    @Override
    @Transactional
    public void transferIn(String aAcctId, String bAcctId, double amount) {
        serviceAClient.transferOut(aAcctId, amount);
        accountService.tryTransferIn(bAcctId, amount);
        serviceCClient.save(aAcctId, bAcctId, amount);
//        throw new RuntimeException();
    }

    @PutMapping("/out")
    @Override
    @Transactional
    public void transferOut(String bAcctId, String aAcctId, double amount) {
        accountService.frozenAmount(bAcctId, amount);
        serviceAClient.transferIn(aAcctId, amount);
        serviceCClient.save(bAcctId, aAcctId, amount);
    }
}
