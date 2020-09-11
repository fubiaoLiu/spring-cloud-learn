package com.xiaoliu.learn.mapper;

import com.xiaoliu.learn.entity.TbSendMessage;
import org.apache.ibatis.annotations.*;

/**
 * @description: 消息Mapper
 * @author: liufb
 * @create: 2020/8/12 9:53
 **/
@Mapper
public interface SendMessageMapper {
    /**
     * 保存发送消息记录
     *
     * @param transactionFlow 交易流水对象
     */
    @Insert("insert into tb_send_message(phone,status) " +
            "values(#{phone},#{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(TbSendMessage transactionFlow);

    /**
     * 更新状态
     *
     * @param id     ID
     * @param status 状态
     */
    @Update("update tb_send_message set status = #{status} where id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
}

