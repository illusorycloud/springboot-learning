package com.illusory.dubboservice.service.impl;

import com.illusory.dubboservice.service.DubboService;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author illusory
 */
@Service(version = "1.0.0")
public class DubboServiceImpl implements DubboService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
