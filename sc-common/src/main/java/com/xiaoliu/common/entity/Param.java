package com.xiaoliu.common.entity;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * @description: 基础参数类
 * @author: FubiaoLiu
 * @date: 2019/2/15
 */
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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getCreateRoleId() {
        return createRoleId;
    }

    public void setCreateRoleId(Long createRoleId) {
        this.createRoleId = createRoleId;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
}
