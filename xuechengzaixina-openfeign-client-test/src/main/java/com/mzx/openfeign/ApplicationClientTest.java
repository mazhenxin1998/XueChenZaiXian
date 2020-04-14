package com.mzx.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 21:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationClientTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationClientTest.class,args);
    }
}
