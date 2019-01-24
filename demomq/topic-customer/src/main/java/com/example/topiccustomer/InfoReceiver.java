package com.example.topiccustomer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 *
 * @author illusoryCloud
 * RabbitListener bindings:绑定队列
 * QueueBinding
 * @Queue value :配置队列名称
 * autoDelete:是否是一个可删除的临时队列
 * @Exchange value:交换器名称
 * type:指定具体的交换器类型
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "${mq.config.queue.info.name}", autoDelete = "true"),
                exchange = @Exchange(value = "${mq.config.exchange}", type = ExchangeTypes.TOPIC),
                key = "*.log.info"
        )
)
public class InfoReceiver {
    /**
     * 接收消息的方法 采用消息队列监听机制
     *
     * @param msg 接收到的消息
     */
    @RabbitHandler
    public void process(String msg) {
        System.out.println("info接收到的消息-->" + msg);
    }
}
