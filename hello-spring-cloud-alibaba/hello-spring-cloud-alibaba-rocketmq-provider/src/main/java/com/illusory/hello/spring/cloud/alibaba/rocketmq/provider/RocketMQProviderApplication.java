package com.illusory.hello.spring.cloud.alibaba.rocketmq.provider;

import com.illusory.hello.spring.cloud.alibaba.rocketmq.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/4/6 22:26
 */
@SpringBootApplication
@EnableBinding({Source.class})
public class RocketMQProviderApplication implements CommandLineRunner {
    @Autowired
    private ProviderService providerService;
    public static void main(String[] args) {
        SpringApplication.run(RocketMQProviderApplication.class, args);
    }
    /**
     * 实现了 CommandLineRunner 接口，只是为了 Spring Boot 启动时执行任务，做测试用
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        providerService.send("Hello RocketMQ");
    }
}
