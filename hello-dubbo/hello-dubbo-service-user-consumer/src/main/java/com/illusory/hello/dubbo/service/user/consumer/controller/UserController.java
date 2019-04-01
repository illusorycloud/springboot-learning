package com.illusory.hello.dubbo.service.user.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.illusory.hello.dubbo.service.user.api.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/4/1 0001
 */
@RestController
public class UserController {
    @Reference(version = "${user.service.version}")
    private UserService userService;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String sayHi() {
        return userService.sayHi();
    }


}
