package com.illusory.hello.spring.cloud.alibab.nacos.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/4/2 23:04
 */
@RestController
public class EchoController {
    /**
     * 用于后面测试负载均衡
     */
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/nacos/{message}")
    public String echo(@PathVariable String message) {
        return "Hello Nacos Discovery " + message + "i am from " + port;
    }
}
