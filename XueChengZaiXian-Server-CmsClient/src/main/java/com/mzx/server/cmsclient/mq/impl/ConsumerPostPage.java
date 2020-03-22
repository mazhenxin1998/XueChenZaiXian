package com.mzx.server.cmsclient.mq.impl;

import com.alibaba.fastjson.JSON;
import com.mzx.server.cmsclient.dao.CmsPageRepository;
import com.mzx.server.cmsclient.mq.IConsumerPostPage;
import com.mzx.server.cmsclient.service.IPageService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 15:58
 */
@Component
@Slf4j
public class ConsumerPostPage implements IConsumerPostPage {

    @Autowired
    private IPageService pageService;

    @Autowired
    private CmsPageRepository pageRepository;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${xuecheng.mq.queuename}",durable = "true"),
            exchange = @Exchange(value = "${xuecheng.mq.exchangename}",durable = "true",type = "direct"),
            key = "${xuecheng.mq.routingkey}"
    ))
    @RabbitHandler
    @Override
    public void postPage(@Payload String message, Channel channel, @Headers Map<String,Object> headers){

        Map map = JSON.parseObject(message, Map.class);
        //获取PageID
        String pageID = (String) map.get("pageID");

        // 将pageID 对应的html文件下载本地目录
        pageService.savePageToServerPath(pageID);
        log.info("---------------     MQ接收到的消息为            Message : "+pageID);
        Long o = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            // 当消息处理完毕则签收
            channel.basicAck(o,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
