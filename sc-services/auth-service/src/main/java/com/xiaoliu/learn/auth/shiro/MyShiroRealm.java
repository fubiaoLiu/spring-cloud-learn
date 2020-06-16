package com.xiaoliu.learn.auth.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaoliu.learn.auth.domain.Permission;
import com.xiaoliu.learn.auth.domain.Role;
import com.xiaoliu.learn.auth.domain.User;
import com.xiaoliu.learn.auth.service.PermissionService;
import com.xiaoliu.learn.auth.service.RoleService;
import com.xiaoliu.learn.auth.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 自定义权限匹配和账号密码匹配
 * @author: liufb
 * @create: 2020/6/15 11:33
 **/
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserService userService;

    /**
     * 设置用户角色、权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo = (User) principals.getPrimaryPrincipal();
        try {
            List<Role> roles = roleService.selectRoleByUser(userInfo);
            for (Role role : roles) {
                authorizationInfo.addRole(role.getName());
            }
            List<Permission> permissions = permissionService.selectPermByUser(userInfo);
            for (Permission perm : permissions) {
                authorizationInfo.addStringPermission(perm.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 通过username从数据库中查找 User对象，可以使用缓存。Shiro默认有时间间隔机制，2分钟内不会重复执行该方法。
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("username", username);
        User user = userService.selectOne(ew);
        if (user == null) {
            return null;
        }
        // 账户冻结
        /*if (userInfo.getState() == 1) {
            throw new LockedAccountException();
        }*/
        return new SimpleAuthenticationInfo(
                // 用户信息
                user,
                // 密码
                user.getPassword(),
                // salt
                ByteSource.Util.bytes(user.getUsername()),
                // realm name
                getName()
        );
    }

}
