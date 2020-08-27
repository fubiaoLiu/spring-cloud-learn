package com.xiaoliu.learn.service.impl;

import com.xiaoliu.learn.constant.TransactionFlowStatus;
import com.xiaoliu.learn.entity.TbTransactionFlow;
import com.xiaoliu.learn.mapper.TransactionFlowMapper;
import com.xiaoliu.learn.service.TransactionFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 账户Service组件
 * @author: liufb
 * @create: 2020/8/24 11:00
 **/
@Service
public class TransactionFlowServiceImpl implements TransactionFlowService {
    @Autowired
    private TransactionFlowMapper transactionFlowMapper;

    @Override
    public Long save(String fromAcctId, String toAcctId, double amount) {
        TbTransactionFlow tbTransactionFlow = new TbTransactionFlow();
        tbTransactionFlow.setFromAcctId(fromAcctId);
        tbTransactionFlow.setToAcctId(toAcctId);
        tbTransactionFlow.setAmount(amount);
        tbTransactionFlow.setStatus(TransactionFlowStatus.IN);
        transactionFlowMapper.insert(tbTransactionFlow);
        return tbTransactionFlow.getId();
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        transactionFlowMapper.updateStatus(id, status);
    }
}
