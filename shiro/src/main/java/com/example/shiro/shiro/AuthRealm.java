package com.example.shiro.shiro;

import com.example.shiro.pojo.Permission;
import com.example.shiro.pojo.Role;
import com.example.shiro.pojo.User;
import com.example.shiro.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取session中的用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        //权限 list集合
        List<String> permission = new ArrayList<>();
        //获取user的角色 然后获取每个角色的权限 接着添加到集合中 最后将权限放入shiro中.
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for (Role r : roles) {
                Set<Permission> permissions = r.getPermissions();
                if (permissions.size() > 0) {
                    for (Permission p : permissions) {
                        permission.add(p.getPermission());
                    }
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证 登陆
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户输入的token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findUserByName(username);
        System.out.println("认证："+user.toString());
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getUpwd(), this.getClass().getName());
    }
}
