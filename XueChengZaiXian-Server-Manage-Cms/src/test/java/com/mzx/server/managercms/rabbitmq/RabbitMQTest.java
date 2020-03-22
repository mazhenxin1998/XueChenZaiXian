package com.mzx.server.managercms.rabbitmq;

import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.mqproducer.IMessageProduceSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/3/2 18:09
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private IMessageProduceSender produceSender;

    @Test
    public void t1(){
        Map<String,Object> message = new HashMap<>();
        message.put("pageID","1111111111");
        produceSender.sendMessage(message,null);
    }


}
