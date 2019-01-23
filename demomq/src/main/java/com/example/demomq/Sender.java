package com.example.demomq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 消息发送者
 *
 * @author illusoryCloud
 */
@Component
public class Sender {
    /**
     * 操作rabbitmq的模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param msg 需要发送的消息
     */
    public void send(String msg) {
        //参数一：消息队列名称
        //参数二：消息
        this.rabbitTemplate.convertAndSend("hello-mq", msg);
    }
}
