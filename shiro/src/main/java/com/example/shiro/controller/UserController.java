package com.example.shiro.controller;

import com.example.shiro.pojo.User;
import com.example.shiro.service.impl.UserServiceImpl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,User inuser,String uname,String upwd) {
        System.out.println("用户名和密码是" + uname + upwd + " User-->" + inuser.toString());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(uname,upwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录
            subject.login(usernamePasswordToken);
            User user = (User) subject.getPrincipal();
            request.getSession().setAttribute("user", user);
            return "index";
        } catch (AuthenticationException e) {
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequiresPermissions(value = "add")
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, String test) {
        System.out.println(test);
        return "test";
    }

    @RequestMapping(value = "/{page}")
    public String show(@PathVariable("page") String page) {
        return page;
    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
