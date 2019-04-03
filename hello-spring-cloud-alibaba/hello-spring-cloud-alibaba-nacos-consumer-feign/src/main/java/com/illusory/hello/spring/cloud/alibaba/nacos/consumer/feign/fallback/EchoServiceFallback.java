package com.illusory.hello.spring.cloud.alibaba.nacos.consumer.feign.fallback;

import com.illusory.hello.spring.cloud.alibaba.nacos.consumer.feign.service.EchoService;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/4/3 21:50
 */
public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(String message) {
        return "请求失败，请检查您的网络";
    }
}
