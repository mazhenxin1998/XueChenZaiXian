package com.mzx.server.managecms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:57
 */
@Configuration
@Component
public class BeanConfig {

        @Bean
        public RestTemplate get(){
            return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        }

}
