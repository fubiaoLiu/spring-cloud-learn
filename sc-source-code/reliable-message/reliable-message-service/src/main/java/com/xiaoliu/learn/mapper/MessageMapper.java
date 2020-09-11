package com.xiaoliu.learn.mapper;

import com.xiaoliu.learn.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

/**
 * @description: 消息Mapper
 * @author: liufb
 * @create: 2020/8/12 9:53
 **/
@Mapper
public interface MessageMapper {
    /**
     * 新增消息
     *
     * @param message 消息对象
     */
    @Insert("insert into message(content,status) values(#{content},#{status})")
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    void insert(Message message);

    /**
     * 确认发送消息
     *
     * @param message 消息对象
     */
    @Update("update message set status = #{status}, confirmed_time = #{confirmedTime} where id = #{id}")
    void confirm(Message message);

    /**
     * 删除消息
     *
     * @param message 消息对象
     */
    @Update("update message set status = #{status}, removed_time = #{removedTime} where id = #{id}")
    void remove(Message message);

    /**
     * 完成消息
     *
     * @param message 消息对象
     */
    @Update("update message set status = #{status}, finished_time = #{finishedTime} where id = #{id}")
    void finish(Message message);
}

