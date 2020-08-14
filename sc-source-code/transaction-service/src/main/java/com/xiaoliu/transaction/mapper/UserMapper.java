package com.xiaoliu.transaction.mapper;

import com.xiaoliu.transaction.entity.User;
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
    @Insert("insert into auth_user(username, password) " +
            "values (" +
            "    #{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void insert(User user);

    /**
     * 修改用户
     *
     * @param user 用户对象
     */
    @Update("update auth_user\n" +
            "set username = #{username,jdbcType=VARCHAR}," +
            "password = #{password,jdbcType=VARCHAR} " +
            "where id = #{id}")
    void update(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @Delete("delete from auth_user where id = #{id}")
    void deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Select("select * from auth_user where id = {id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password")
    })
    User selectByPrimaryKey(@Param("id") Long id);
}

