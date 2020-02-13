package com.mzx.freemaker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 20:04
 */
@Configuration
@Component
public class BeanConfig {


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }


}
