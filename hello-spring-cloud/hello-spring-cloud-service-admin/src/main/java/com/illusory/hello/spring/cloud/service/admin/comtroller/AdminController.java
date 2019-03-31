package com.illusory.hello.spring.cloud.service.admin.comtroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/3/30 22:32
 */
@RestController
public class AdminController {
    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "message") String message) {
        return String.format("hello message is : %s port is : %s", message, port);
    }
}
