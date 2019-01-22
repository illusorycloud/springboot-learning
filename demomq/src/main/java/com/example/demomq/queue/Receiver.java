package com.example.demomq.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 *
 * @author illusoryCloud
 */
@Component
public class Receiver {
    /**
     * 接收消息的方法 采用消息队列监听机制
     * @param msg 接收到的消息
     */
    @RabbitListener(queues = {"hello-mq"})
    public void process(String msg) {
        System.out.println("接收到的消息-->"+msg);
    }
}
