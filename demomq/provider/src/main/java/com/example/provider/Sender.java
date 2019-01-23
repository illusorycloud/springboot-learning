package com.example.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

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
    //交换器
    @Value(value = "${mq.config.exchange}")
    private String exchange;
    //路由键
    @Value(value = "${mq.config.queue.info.routing.key}")
    private String routingKey;
    /**
     * 发送消息
     *
     * @param msg 需要发送的消息
     */
    public void send(String msg) {
        //参数一：消息队列名称
        //参数二：消息
        this.rabbitTemplate.convertAndSend(this.exchange,this.routingKey, msg);
    }
}
