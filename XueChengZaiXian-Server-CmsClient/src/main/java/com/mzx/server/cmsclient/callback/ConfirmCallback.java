package com.mzx.server.cmsclient.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 21:02
 *
 *      消息确认机制：保障100%投递成功的一种机制
 *             指生产者投递消息之后，如果Broker接受到该消息那么就返回ack为true
 *             如果Broker没有接受到该消息那么就返回ack为false
 *
 */
@Component
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("--------------------消息确认机制----------------");
        if(!ack){
            //  消息投递不成功的处理
        }else{
            System.out.println("CorrelationData     :"+correlationData);
            System.err.println("ERROR: 消息投递出错："+cause);
        }

    }
}
