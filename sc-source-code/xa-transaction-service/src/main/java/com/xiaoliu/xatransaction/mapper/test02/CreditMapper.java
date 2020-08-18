package com.xiaoliu.xatransaction.mapper.test02;

import com.xiaoliu.xatransaction.entity.Credit;
import org.apache.ibatis.annotations.*;

/**
 * @description: 积分Mapper
 * @author: liufb
 * @create: 2020/8/12 9:53
 **/
@Mapper
public interface CreditMapper {
    /**
     * 初始化用户积分数据
     *
     * @param credit 积分对象
     */
    @Insert("insert into credit (user_account_id, point) values (#{userAccountId}, #{point})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void insert(Credit credit);

    /**
     * 增加积分
     *
     * @param userAccountId 用户账号id
     */
    @Update("UPDATE credit "
            + "SET point = point + #{updatedPoint} "
            + "WHERE user_account_id=#{userAccountId}")
    void increment(@Param("userAccountId") Long userAccountId,
                   @Param("updatedPoint") Double updatedPoint);
}

