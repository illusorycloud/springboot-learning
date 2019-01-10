package com.example.shiro.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //强转 获取token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户输入的密码
        char[] password = usernamePasswordToken.getPassword();
        String inputPassword = new String(password);
        //获取数据库中的密码
        String realPassword = (String) info.getCredentials();
        //对比
        return this.equals(inputPassword, realPassword);
    }
}
