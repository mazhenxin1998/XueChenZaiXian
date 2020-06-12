package com.mzx.media;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 如果不使用@ComponentScan("com.mzx") 那么swagger则不会起作用.
 *
 * @author ZhenXinMa
 * @date 2020/5/12 21:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.mzx"})
public class SpringApplicationServerMediaApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationServerMediaApp.class, args);
    }
}
