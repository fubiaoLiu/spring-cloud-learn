package com.xiaoliu.learn.service;

/**
 * @description: 账户Service组件接口
 * @author: liufb
 * @create: 2020/8/24 10:58
 **/
public interface AccountService {
    /**
     * 冻结资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    void frozenAmount(String acctId, double amount);

    /**
     * 解冻资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    void unfrozenAmount(String acctId, double amount);

    /**
     * 转出资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    void transferOut(String acctId, double amount);

    /**
     * try转入资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    void tryTransferIn(String acctId, double amount);

    /**
     * confirm转入资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    void confirmTransferIn(String acctId, double amount);

    /**
     * cancel转入资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    void cancelTransferIn(String acctId, double amount);
}
