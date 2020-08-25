package com.xiaoliu.learn.controller;

/**
 * @description: 转账Service组件接口
 * @author: liufb
 * @create: 2020/8/24 13:19
 **/
public interface TransferService {
    /**
     * A服务账号转账到B服务装账号
     *
     * @param aAcctId A服务账号
     * @param bAcctId B服务账号
     * @param amount  金额
     */
    void transferIn(String aAcctId, String bAcctId, double amount);

    /**
     * B服务账号转账到A服务装账号
     *
     * @param bAcctId B服务账号
     * @param aAcctId A服务账号
     * @param amount  金额
     */
    void transferOut(String bAcctId, String aAcctId, double amount);
}
