package com.illusory.hello.dubbo.service.user.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/4/1 0001
 */
@Service(version = "${user.service.version}")
public class UserService implements com.illusory.hello.dubbo.service.user.api.UserService {
    @Value("${dubbo.protocol.port}")
    private String prot;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @Override
    public String sayHi() {
//        return String.format("Hello Dubbo! I am from port: %s", prot);
        throw new RuntimeException("Exception to show hystrix enabled.");
    }

}
