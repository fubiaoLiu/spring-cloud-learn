package com.xiaoliu.learn.constant;


/**
 * @description: 交易流水状态
 * @author: liufb
 * @create: 2020/8/24 17:37
 **/
public class TransactionFlowStatus {
    private TransactionFlowStatus() {
    }

    /**
     * 交易中
     */
    public static final int IN = 0;

    /**
     * 交易成功
     */
    public static final int SUCCESS = 1;

    /**
     * 交易失败
     */
    public static final int FAILURE = 2;
}
