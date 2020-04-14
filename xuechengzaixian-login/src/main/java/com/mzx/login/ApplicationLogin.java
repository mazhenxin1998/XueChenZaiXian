package com.mzx.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhenXinMa
 * @date 2020/4/10 16:04
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationLogin {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLogin.class,args);
    }
}
