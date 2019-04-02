package com.illusory.hello.spring.cloud.alibaba.nacos.consumer.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/4/2 23:31
 */
@FeignClient(value = "nacos-provider")
public interface EchoService {
    @GetMapping(value = "/nacos/{message}")
    String echo(@PathVariable String message);
}
