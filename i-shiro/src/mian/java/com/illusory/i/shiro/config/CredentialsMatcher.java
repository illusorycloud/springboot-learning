//package com.illusory.i.shiro.config;
//
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
//
//public class CredentialsMatcher extends SimpleCredentialsMatcher {
//    @Override
//    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        //强转 获取token
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        //获取用户输入的密码
//        char[] password = usernamePasswordToken.getPassword();
//        String inputPassword = new String(password);
////        Md5Hash md5Hash = new Md5Hash(inputPassword);
//        //获取数据库中的密码
//        String realPassword = (String) info.getCredentials();
//        System.out.println("输入的密码"+inputPassword);
//        System.out.println("数据库中的密码"+realPassword);
//        //对比
//        return this.equals(inputPassword, realPassword);
//    }
//}
