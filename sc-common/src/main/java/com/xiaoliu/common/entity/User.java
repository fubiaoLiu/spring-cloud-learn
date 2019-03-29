package com.xiaoliu.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @description: RocketMQ消息
 * @author: FubiaoLiu
 * @date: 2019/3/28
 */
@Getter
@Setter
public class User {
    @Transient
    private Long id;
    @Transient
    private String username;
    @Transient
    private String password;
    @Transient
    private Integer sex;
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
}
