package com.illusory.hello.spring.cloud.web.admin.ribbon.controller;

import com.illusory.hello.spring.cloud.web.admin.ribbon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller层和以前写法一样只是Service层有所改变
 *
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/3/30 22:57
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String sayHello(String messgae) {
        return adminService.sayHello(messgae);
    }
}
