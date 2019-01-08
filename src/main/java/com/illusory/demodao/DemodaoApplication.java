package com.illusory.demodao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.illusory.demodao.mapper") //Mapper扫描
public class DemodaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemodaoApplication.class, args);
    }

}

