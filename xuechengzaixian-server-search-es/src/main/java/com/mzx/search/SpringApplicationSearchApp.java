package com.mzx.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 17:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringApplicationSearchApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(SpringApplicationSearchApp.class,args);
    }

}
