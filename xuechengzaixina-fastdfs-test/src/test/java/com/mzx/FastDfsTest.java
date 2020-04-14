package com.mzx;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author ZhenXinMa
 * @date 2020/4/5 16:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDfsTest {

    @Test
    public void upload(){

        try {
            /*加载配置文件*/
            ClientGlobal.initByProperties("fastdfs-client.properties");

            /* 声明TrackerClient 用于请求TrackerClient*/
            TrackerClient trackerClient = new TrackerClient();

            /*使用TrackerClient连接Tracker*/
            TrackerServer connection = trackerClient.getConnection();

            /*通过使用TrackerClient 获取的连接获取Storage*/
            StorageServer storeStorage = trackerClient.getStoreStorage(connection);

            StorageClient1 storageClient1 = new StorageClient1(connection, storeStorage);
            /*向Storage上传文件 通过StorageClient*/
            String filePath = "D:/tmp/default.png";
            String fileID = storageClient1.upload_appender_file1(filePath, "png", null);
            System.out.println(fileID);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
