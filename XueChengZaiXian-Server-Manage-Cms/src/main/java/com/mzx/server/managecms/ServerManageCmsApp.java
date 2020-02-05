package com.mzx.server.managecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 16:17
 */
@SpringBootApplication
@EntityScan(value = "com.mzx.framework.model.cms")
@ComponentScan(basePackages = {"com.mzx.api"})
@ComponentScan(basePackages = {"com.mzx.server.managecms"})
public class ServerManageCmsApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerManageCmsApp.class,args);
    }
}
