package com.example.topicprovider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 消息发送者
 *
 * @author illusoryCloud
 */
@Component
public class UserSender {
    /**
     * 操作rabbitmq的模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //交换器
    @Value(value = "${mq.config.exchange}")
    private String exchange;
    /**
     * 发送消息
     *
     * @param msg 需要发送的消息
     */
    public void send(String msg) {
        //参数一：消息队列名称
        //参数二：消息
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.info", "info"+msg);
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.debug", "debug"+msg);
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.error", "error"+msg);
    }
}
