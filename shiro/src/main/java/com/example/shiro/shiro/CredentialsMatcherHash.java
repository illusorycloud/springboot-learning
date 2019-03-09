package com.example.shiro.shiro;

import com.example.shiro.pojo.User;
import com.example.shiro.service.UserService;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialsMatcherHash extends SimpleCredentialsMatcher {
    @Autowired
    UserService service;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //强转 获取token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户输入的密码
        char[] password = usernamePasswordToken.getPassword();
        String inputPassword = new String(password);
        String username = usernamePasswordToken.getUsername();
        User userByName = service.findUserJustByName(username);
        String salt = userByName.getSalt();
        //这个盐值是从数据库查出来的
        Md5Hash md5Hash = new Md5Hash(inputPassword, salt);
        String inputMD5Hash = new String(String.valueOf(md5Hash));
        //获取数据库中的密码
        String realPassword = (String) info.getCredentials();
        System.out.println("输入的密码" + inputPassword);
        System.out.println("输入的密码加密" + md5Hash);
        System.out.println("数据库中的密码" + realPassword);
        //对比
        return this.equals(inputMD5Hash, realPassword);
    }
}
