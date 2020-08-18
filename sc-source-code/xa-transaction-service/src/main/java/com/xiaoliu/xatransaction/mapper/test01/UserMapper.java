package com.xiaoliu.xatransaction.mapper.test01;

import com.xiaoliu.xatransaction.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @description: 用户Mapper
 * @author: liufb
 * @create: 2020/8/12 9:53
 **/
@Mapper
public interface UserMapper {
    /**
     * 新增用户
     *
     * @param user 用户对象
     */
    @Insert("insert into user(username, password) " +
            "values (" +
            "    #{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void insert(User user);
}

