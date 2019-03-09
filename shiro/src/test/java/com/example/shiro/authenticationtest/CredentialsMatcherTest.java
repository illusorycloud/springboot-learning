package com.example.shiro.authenticationtest;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码校验类
 */
public class CredentialsMatcherTest extends SimpleCredentialsMatcher {
    /**
     * 校验密码
     *
     * @param token
     * @param info
     * @return 密码校验结果
     * {@link AuthRealmTest#doGetAuthenticationInfo(AuthenticationToken)}
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //1.强转
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //2.获取用户输入的密码
        char[] password = usernamePasswordToken.getPassword();
        String pwd = new String(password);
        //3.获取数据库中的真实密码
        //这个info就是前面AuthRealmTest类中的doGetAuthenticationInfo返回的info
        String relPwd = (String) info.getCredentials();
        //4.返回校验结果
        return this.equals(pwd, relPwd);

    }
}
