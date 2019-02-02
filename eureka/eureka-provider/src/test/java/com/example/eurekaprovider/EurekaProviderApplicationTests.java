package com.example.eurekaprovider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaProviderApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Before
    public void before() {
        System.out.println("before 初始化工作");
    }

    @After
    public void after() {
        System.out.println("after 日志记录");
    }

}

