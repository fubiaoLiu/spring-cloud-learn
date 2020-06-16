package com.xiaoliu.learn.auth.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Set;

/**
 * @description: 用户类
 * @author: liufb
 * @create: 2020/5/18 16:05
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_user")
public class User implements AuthCachePrincipal, Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String salt;
    private transient Set<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 密码盐.
     *
     * @return 盐
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    @Override
    public String getAuthCacheKey() {
        return "auth_cache:" + username;
    }
}
