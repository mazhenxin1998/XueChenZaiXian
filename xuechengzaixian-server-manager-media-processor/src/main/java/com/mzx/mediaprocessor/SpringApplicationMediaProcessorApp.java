package com.mzx.mediaprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @EnableAsync 启动线程池.
 * @author ZhenXinMa
 * @date 2020/5/18 15:31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@ComponentScan(basePackages = {"com.mzx"})
public class SpringApplicationMediaProcessorApp {
    public static void main(String[] args) {

        SpringApplication.run(SpringApplicationMediaProcessorApp.class,args);
    }
}
