package com.mzx.search;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhenXinMa
 * @date 2020/4/24 11:04
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mzx.api"})
@ComponentScan(basePackages = {"com.mzx.search.config"})
@ComponentScan(basePackages = {"com.mzx"}) // 扫描当前工程中 com.mzx包下所有的类。
public class SpringApplicationSearchServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationSearchServerApp.class,args);
    }
}
