package com.mzx.search.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 17:39
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @GetMapping(value = "/t")
    public String t1(){

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("mzx");
        try {
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            return "SUCCESS 增加 index";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "FALL  add  index";
    }

}
