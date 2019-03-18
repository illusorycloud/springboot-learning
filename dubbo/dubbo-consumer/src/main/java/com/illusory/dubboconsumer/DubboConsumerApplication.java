package com.illusory.dubboconsumer;

import com.illusory.dubboprovider.HelloServer;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DubboConsumerApplication {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "1.0.0", url = "dubbo://localhost:12345")
    private HelloServer helloServer;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(helloServer.sayHello("mercyblitz"));
        };
    }
}
