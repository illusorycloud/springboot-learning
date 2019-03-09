package com.example.fanoutprovider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutProviderApplicationTests {
    @Autowired
    private OrdersSender ordersSender;

    @Test
    public void testSend() {
        this.ordersSender.send("Hello RabbitMQ...");
    }

}

