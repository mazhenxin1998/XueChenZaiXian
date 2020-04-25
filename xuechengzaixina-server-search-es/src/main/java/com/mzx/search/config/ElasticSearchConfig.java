package com.mzx.search.config;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ZhenXinMa
 * @date 2020/4/25 22:18
 */
@Configuration
public class ElasticSearchConfig {


    @Value(value = "${xuechengzaixian.elasticsearch.host}")
    private String host;

    @Value(value = "${xuechengzaixian.elasticsearch.port}")
    private int port;

    /**
     * 目前只针对单机环境下.
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("127.0.0.1",9200,"http")));
        return client;
    }



}
