package com.mzx.media.mq.impl;

import com.alibaba.fastjson.JSON;
import com.mzx.media.mq.MediaRabbitMessageProducer;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 10:27
 */
@Component
public class MediaRabbitMessageProducerImpl implements MediaRabbitMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${xuechengzaixian.mq.exchange}")
    private String exchange = "";

    @Value(value = "${xuechengzaixian.mq.routingkey‐media‐video}")
    private String routingKey = "";

    @Override
    public void sendMessage(Map<String, Object> message, Map<String, Object> headers, String mediaFileID) {

        /*发送消息的时候应该将MediaID作为消息ID发送过去.且应该附带当前用户的ID.*/
        System.out.println("消息发送: " + mediaFileID);
        CorrelationData correlationData = new CorrelationData(mediaFileID);
        String s = JSON.toJSONString(message);
        rabbitTemplate.convertAndSend(exchange, routingKey, s, correlationData);
        System.out.println("消息发送结束: " + mediaFileID);
    }

}
