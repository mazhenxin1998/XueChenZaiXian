package com.mzx.server.managecms;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 16:17
 */
@SpringBootApplication
@EntityScan(value = "com.mzx.framework.model.cms")
@ComponentScan(basePackages = {"com.mzx.api"})
@ComponentScan(basePackages = {"com.mzx.common"})
@ComponentScan(basePackages = {"com.mzx.server.managecms"})
@ComponentScan(basePackages = {"com.mzx.server.managecms.config"})
public class ServerManageCmsApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerManageCmsApp.class,args);
    }
}
