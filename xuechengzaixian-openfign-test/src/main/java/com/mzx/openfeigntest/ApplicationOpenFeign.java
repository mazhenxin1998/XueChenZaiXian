package com.mzx.openfeigntest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 20:56
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationOpenFeign {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationOpenFeign.class,args);
    }
}
