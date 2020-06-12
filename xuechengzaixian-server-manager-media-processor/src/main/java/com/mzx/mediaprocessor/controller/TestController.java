package com.mzx.mediaprocessor.controller;

import com.alibaba.fastjson.JSON;
import com.mzx.mediaprocessor.callback.RabbitReturnCallBack;
import com.sun.org.apache.bcel.internal.generic.NEW;
import freemarker.template.Template;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ZhenXinMa
 * @date 2020/5/18 18:03
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/{param}")
    public String t1(@PathVariable String param){


        Map<String,Object> messages = new HashMap<>();
        messages.put("mediaId",param);
        Map<String,Object> headers = new HashMap<>();
        String id = UUID.randomUUID().toString();
        CorrelationData data = new CorrelationData(id);
        String string = JSON.toJSONString(messages);
        /*return机制触发： 当前指定的Exchange不存在或者路由Key找不到.  routingkey_media_video*/
        rabbitTemplate.convertAndSend("ex_media_processor","routingkey_media_video",string,data);
        return "SUCCESS";
    }

}
