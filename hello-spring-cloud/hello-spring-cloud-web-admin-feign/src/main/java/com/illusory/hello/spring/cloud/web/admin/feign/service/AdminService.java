package com.illusory.hello.spring.cloud.web.admin.feign.service;

import com.illusory.hello.spring.cloud.web.admin.feign.hystrix.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/3/30 23:14
 */
@FeignClient(value = "hello-spring-cloud-service-admin",fallback = AdminServiceHystrix.class)
public interface AdminService {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "message") String message);
}
