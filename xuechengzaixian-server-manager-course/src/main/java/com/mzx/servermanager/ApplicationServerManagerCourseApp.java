package com.mzx.servermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 15:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.mzx"})
@ComponentScan(basePackages = {"com.mzx.api"})
@ComponentScan(basePackages = {"com.mzx.common"})
@ComponentScan(basePackages = {"com.mzx.servermanager"})
public class ApplicationServerManagerCourseApp {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationServerManagerCourseApp.class,args);
    }
}
