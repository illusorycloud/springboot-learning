package com.illusory.hello.spring.cloud.web.admin.feign.hystrix;

import com.illusory.hello.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/3/31 15:01
 */
@Component
public class AdminServiceHystrix implements AdminService {
    @Override
    public String sayHello(String message) {
        return String.format("Hello，your message is %s but request error", message);
    }
}
