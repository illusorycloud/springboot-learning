package com.illusory.hello.spring.cloud.web.admin.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/3/30 22:46
 */
@Configuration
public class RestTemplateConfiguration {
    @Bean
    @LoadBalanced//负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
