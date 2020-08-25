package com.xiaoliu.learn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 账号Mapper
 * @author: liufb
 * @create: 2020/8/12 9:53
 **/
@Mapper
public interface AccountMapper {
    /**
     * 增加资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set amount = amount + #{amount} where acct_id = #{acctId}")
    void increaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * 减少资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set amount = amount - #{amount} where acct_id = #{acctId}")
    void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * 冻结资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set amount = amount - #{amount},frozen = frozen + #{amount} where acct_id = #{acctId}")
    void frozenAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * 解冻资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set amount = amount + #{amount},frozen = frozen - #{amount} where acct_id = #{acctId}")
    void unfrozenAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * 转出资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set frozen = frozen - #{amount} where acct_id = #{acctId}")
    void transferOut(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * try转入资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set frozen = frozen + #{amount} where acct_id = #{acctId}")
    void tryTransferIn(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * confirm转入资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set amount = amount + #{amount},frozen = frozen - #{amount} where acct_id = #{acctId}")
    void confirmTransferIn(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);

    /**
     * cancel转入资金
     *
     * @param acctId 用户ID
     * @param amount 金额
     */
    @Update("update tb_account_two set frozen = frozen - #{amount} where acct_id = #{acctId}")
    void cancelTransferIn(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount);
}

