package com.mzx.media.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 10:20
 */
@Component
public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {

        System.out.println("Confirm机制触发...");
        System.out.println("消息ID: " + correlationData);
        if(ack){

            // 表示BROKER接受到消息.

        }else{
            System.out.println("BROKER没有接受到发送的消息的原因:  "+s);
            // BROKER没有接受到发送的消息.
        }

    }
}
