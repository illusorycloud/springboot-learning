package com.illusory.i.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author illusory
 * @version 1.0.0
 * @date 2019/4/15
 */
@SpringBootApplication
@MapperScan("com.illusory.i.shiro.mapper")
public class IShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(IShiroApplication.class, args);
    }
}
