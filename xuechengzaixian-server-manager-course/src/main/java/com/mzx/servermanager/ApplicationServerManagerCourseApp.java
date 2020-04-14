package com.mzx.servermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 开启事务支持：@EnableTransactionManagement.
 * @author ZhenXinMa
 * @date 2020/3/22 15:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients
@EnableCaching
@ComponentScan(basePackages = {"com.mzx"})
@ComponentScan(basePackages = {"com.mzx.api"})
@ComponentScan(basePackages = {"com.mzx.common"})
@ComponentScan(basePackages = {"com.mzx.servermanager"})
public class ApplicationServerManagerCourseApp {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationServerManagerCourseApp.class,args);
    }
}
