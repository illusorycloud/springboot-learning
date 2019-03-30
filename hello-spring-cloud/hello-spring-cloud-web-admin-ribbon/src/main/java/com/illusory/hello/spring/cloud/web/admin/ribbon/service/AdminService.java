package com.illusory.hello.spring.cloud.web.admin.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 这里的Service层不再是调用dao层了，而是调用远程服务
 *
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/3/30 22:51
 */
@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    public String sayHello(String message) {
        //注册中心管理ip和端口号 访问时只需要提供服务名称就能访问到了
        return restTemplate.getForObject("http://hello-spring-cloud-service-admin/hello?message=" + message, String.class);
    }
}
