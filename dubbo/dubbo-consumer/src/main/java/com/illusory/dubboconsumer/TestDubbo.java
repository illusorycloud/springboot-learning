package com.illusory.dubboconsumer;

import com.illusory.dubboservice.service.DubboService;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;

/**
 * @author illusory
 */
public class TestDubbo {
    @Reference
    /**
     *   从ZooKeeper注册中心获取DubboService
     */
    private DubboService dubboServer;

    @Test
    public void test() {
        String s = dubboServer.sayHello("illusory");
        System.out.println(s);
    }
}
