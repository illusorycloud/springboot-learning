package com.example.eurekaserverclu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaserverCluApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverCluApplication.class, args);
    }

}

