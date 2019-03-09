package com.example.shiro.authenticationtest;

import com.example.shiro.pojo.Permission;
import com.example.shiro.pojo.Role;
import com.example.shiro.pojo.User;
import com.example.shiro.service.UserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class AuthRealmTest extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取session中的用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();

        //2.去数据库查询当前user的权限
        List<String> strings = userService.selectPermissionByUserId(user.getUid());

        //3.将权限放入shiro中.
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(strings);
        //4.返回授权信息AuthorizationInfo
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException ex
     *                                 密码校验在{@link CredentialsMatcherTest#doCredentialsMatch(AuthenticationToken, AuthenticationInfo)}
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.将用户输入的token 就是authenticationToken强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2.获取用户名
        String username = usernamePasswordToken.getUsername();
        //3.数据库中查询出user对象
        User user = userService.findUserByName(username);
        //4.查询出这个user的权限
        Set<Role> roles = user.getRoles();
        for (Role r : roles) {
            Set<Permission> permissions = r.getPermissions();
            for (Permission p : permissions) {
                String permission = p.getPermission();
                System.out.println("权限--》" + permission);
            }
        }
        //5.返回认证信息AuthenticationInfo 这里是没进行密码校验的 密码校验在CredentialsMatcherTest类中
        return new SimpleAuthenticationInfo(user, user.getUpwd(), this.getClass().getName());
    }
}
