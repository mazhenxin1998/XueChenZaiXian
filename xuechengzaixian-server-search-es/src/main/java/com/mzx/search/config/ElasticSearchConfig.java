package com.mzx.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 17:40
 */
@Configuration
@Component
public class ElasticSearchConfig implements Serializable
{

    @Bean
    public RestHighLevelClient restHighLevelClient()
    {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1",
                9200, "http")));

        return client;
    }




}
