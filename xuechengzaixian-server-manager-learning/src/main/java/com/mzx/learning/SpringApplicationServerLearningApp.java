package com.mzx.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 17:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.mzx"})
public class SpringApplicationServerLearningApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationServerLearningApp.class, args);
    }
}
