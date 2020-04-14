package com.mzx.filesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author ZhenXinMa
 * @date 2020/4/5 17:43
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationFileSystem {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationFileSystem.class,args);
    }
}
