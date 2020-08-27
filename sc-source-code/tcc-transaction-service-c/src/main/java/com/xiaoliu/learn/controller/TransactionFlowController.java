package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.service.TransactionFlowService;
import com.xiaoliu.learn.service.TransactionFlowServiceApi;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 账户Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@Compensable(
        interfaceClass = TransactionFlowServiceApi.class,
        confirmableKey = "transactionFlowServiceConfirm",
        cancellableKey = "transactionFlowServiceCancel")
@RestController
public class TransactionFlowController implements TransactionFlowServiceApi, CompensableContextAware {
    private CompensableContext compensableContext;

    @Autowired
    private TransactionFlowService transactionFlowService;

    @Override
    @Transactional
    public void save(String fromAcctId, String toAcctId, double amount) {
        Long id = transactionFlowService.save(fromAcctId, toAcctId, amount);
        compensableContext.setVariable("transactionFlowId", id);
    }

    @Override
    public void setCompensableContext(CompensableContext aware) {
        this.compensableContext = aware;
    }

}
