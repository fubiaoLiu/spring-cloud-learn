package com.xiaoliu.learn.controller;

import com.xiaoliu.learn.constant.TransactionFlowStatus;
import com.xiaoliu.learn.service.TransactionFlowService;
import com.xiaoliu.learn.service.TransactionFlowServiceApi;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 账户Controller
 * @author: liufb
 * @create: 2020/7/31 11:21
 **/
@RequestMapping("//transactionFlow/confirm")
@Service("transactionFlowServiceConfirm")
public class TransactionFlowServiceConfirm implements TransactionFlowServiceApi, CompensableContextAware {
    private CompensableContext compensableContext;

    @Autowired
    private TransactionFlowService transactionFlowService;

    @Override
    @Transactional
    public void save(String fromAcctId, String toAcctId, double amount) {
        Long id = (Long) compensableContext.getVariable("transactionFlowId");
        transactionFlowService.updateStatus(id, TransactionFlowStatus.SUCCESS);
    }

    @Override
    public void setCompensableContext(CompensableContext aware) {
        this.compensableContext = aware;
    }

}
