package com.mzx.server.managercms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mzx.server.managecms.ServerManageCmsApp;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 16:44
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.mzx.server.managecms.config"})
public class GirdFSTemplate {

    @Autowired
    GridFsTemplate template;

    @Autowired
    GridFSBucket gridFSBucket;

    /**
     *   存储文件
     */
    @Test
    public void test() throws FileNotFoundException {

        // 要存储的文件
        File file = new File("D:/test1.html");

        // 定义输入流
        FileInputStream inputStream = new FileInputStream(file);

        // 向GirdFS存储文件
        ObjectId o = template.store(inputStream, "测试", "");

        // 得到文件ID
        String id = o.toString();

        System.out.println(id);
        System.out.println(1);
        //5e426e30ab23493928c486c7
    }

    /**
     *  根据ID获取文件
     */
    @Test
    public void get() throws IOException {

        String id = "5e426e30ab23493928c486c7";

        // 根据ID查询文件
        GridFSFile file = template.findOne(Query.query(Criteria.where("_id").is(id)));

        // 下载流
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());

        // 获取流对象
        GridFsResource resource = new GridFsResource(file,downloadStream);

        // 获取流中数据
        String s = IOUtils.toString(resource.getInputStream(),"UTF-8");
        System.out.println(s);
        System.out.println(1);
    }

    @Test
    public void t2 (){

    }

}
