package com.mzx.freemaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 14:09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FreemakerApp {
    public static void main(String[] args) {
        SpringApplication.run(FreemakerApp.class,args);
    }
}
