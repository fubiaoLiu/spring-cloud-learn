package com.xiaoliu.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * @description: 基础参数类
 * @author: FubiaoLiu
 * @date: 2019/2/15
 */
@Data
public class Param {
    /**
     * 创建者ID
     */
    @Column(name = "create_user_id")
    private Long createUserId;
    /**
     * 创建角色id
     */
    @Column(name = "create_role_id")
    private Long createRoleId;
    @Transient
    private String createUsername;
    @Transient
    private Integer start;
    @Transient
    private Integer limit;
}
