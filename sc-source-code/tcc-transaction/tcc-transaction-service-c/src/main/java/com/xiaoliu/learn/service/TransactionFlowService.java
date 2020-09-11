package com.xiaoliu.learn.service;

/**
 * @description: 账户Service组件接口
 * @author: liufb
 * @create: 2020/8/24 10:58
 **/
public interface TransactionFlowService {
    /**
     * 保存交易流水
     *
     * @param fromAcctId 源账户
     * @param toAcctId   目标账户
     * @param amount     金额
     * @return ID
     */
    Long save(String fromAcctId, String toAcctId, double amount);

    /**
     * 更新状态
     *
     * @param id     ID
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);
}
