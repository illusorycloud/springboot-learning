package com.illusory.hello.spring.cloud.alibaba.rocketmq.consumer.service;

        import org.springframework.cloud.stream.annotation.StreamListener;
        import org.springframework.stereotype.Service;

/**
 * @author illusoryCloud
 * @version 1.0.0
 * @date 2019/4/7 00:04
 */
@Service
public class ConsumerReceive {

    @StreamListener("input")
    public void receiveInput(String message) {
        System.out.println("Receive input: " + message);
    }
}
