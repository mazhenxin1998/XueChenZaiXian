package com.mzx.server.cmsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 14:54
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(value = "com.mzx.framework.model.cms")
@ComponentScan(basePackages = {"com.mzx.common"})
@ComponentScan(basePackages = {"com.mzx.server.cmsclient"})
@ComponentScan(basePackages = {"com.mzx.server.cmsclient.config"})
public class ServerCmsClientApp {

    public static void main(String[] args) {

        SpringApplication.run(ServerCmsClientApp.class,args);
    }
}
