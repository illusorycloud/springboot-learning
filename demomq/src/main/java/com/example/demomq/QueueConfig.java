package com.example.demomq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息队列配置
 *
 * @author illusoryCloud
 */
@Configuration
public class QueueConfig {
    /**
     * 创建队列
     *
     * @return 消息队列
     */
    @Bean
    public Queue creatQueue() {
        return new Queue("hello-mq");
    }
}
