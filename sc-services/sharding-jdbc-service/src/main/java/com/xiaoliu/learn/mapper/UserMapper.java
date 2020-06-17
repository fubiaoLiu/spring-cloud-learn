package com.xiaoliu.learn.mapper;

import com.xiaoliu.learn.entity.User;
import com.xiaoliu.learn.entity.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 用户管理mapper
 * @author: liufb
 * @create: 2020/4/21 14:58
 **/
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    User selectByPrimaryKey(@Param("id") Long id);
}