package com.mzx.openfeign.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 21:26
 */
@Configuration
@Component
public class BeanConfig {

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//    }


}
