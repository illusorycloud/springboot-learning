package com.illusory.hello.spring.cloud.alibaba.nacos.consumer.feign.controller;

import com.illusory.hello.spring.cloud.alibaba.nacos.consumer.feign.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/4/2 23:32
 */
@RestController
public class EchoController {
    @Autowired
    private EchoService echoService;

    @GetMapping(value = "/nacos/{message}")
    private String echo(@PathVariable String message) {
        return echoService.echo(message);
    }
}
