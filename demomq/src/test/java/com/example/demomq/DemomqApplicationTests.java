package com.example.demomq;

import com.example.demomq.queue.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemomqApplicationTests {
    @Autowired
    private Sender sender;

    @Test
    public void mqTest() throws InterruptedException {
        while(true) {
            this.sender.send("hello RabbitMQ");
            Thread.sleep(1000);
        }
    }
}

