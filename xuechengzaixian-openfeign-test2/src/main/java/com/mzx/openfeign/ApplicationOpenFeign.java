package com.mzx.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 21:05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationOpenFeign {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationOpenFeign.class,args);
    }
}
