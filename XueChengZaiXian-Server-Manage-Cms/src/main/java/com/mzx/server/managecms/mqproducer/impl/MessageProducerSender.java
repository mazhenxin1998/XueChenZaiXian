package com.mzx.server.managecms.mqproducer.impl;

import com.alibaba.fastjson.JSON;
import com.mzx.server.managecms.callback.ConfirmCallback;
import com.mzx.server.managecms.callback.ReturnCallback;
import com.mzx.server.managecms.mqproducer.IMessageProduceSender;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @author ZhenXinMa
 * @date 2020/3/2 16:50
 */
@Component
public class MessageProducerSender implements IMessageProduceSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ConfirmCallback confirmCallback;

    @Autowired
    private ReturnCallback returnCallback;

    @Value(value = "${xuecheng.mq.exchangename}")
    private String exchange = "";

    @Value(value = "${xuecheng.mq.routingkey}")
    private String routingKey = "";

    /**
     *   将object作为消息发送过去
     *   消息格式为json格式
     * @param message
     * @param properties
     */
    @Override
    public void sendMessage(Map<String,Object> message , Map<String,Object> properties){

        template.setReturnCallback(returnCallback);
        template.setConfirmCallback(confirmCallback);
        String uuid = UUID.randomUUID().toString();
        CorrelationData data = new CorrelationData(uuid);
        String string = JSON.toJSONString(message);
        template.convertAndSend(exchange,routingKey,string,data);

    }

}
