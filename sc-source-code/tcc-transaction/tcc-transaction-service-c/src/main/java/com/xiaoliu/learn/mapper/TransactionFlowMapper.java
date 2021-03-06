package com.xiaoliu.learn.mapper;

import com.xiaoliu.learn.entity.TbTransactionFlow;
import org.apache.ibatis.annotations.*;

/**
 * @description: 账号Mapper
 * @author: liufb
 * @create: 2020/8/12 9:53
 **/
@Mapper
public interface TransactionFlowMapper {
    /**
     * 保存流水
     *
     * @param transactionFlow 交易流水对象
     */
    @Insert("insert into tb_transaction_flow(from_acct_id,to_acct_id,amount,status) " +
            "values(#{fromAcctId},#{toAcctId},#{amount},#{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(TbTransactionFlow transactionFlow);

    /**
     * 更新状态
     *
     * @param id     ID
     * @param status 状态
     */
    @Update("update tb_transaction_flow set status = #{status} where id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
}

