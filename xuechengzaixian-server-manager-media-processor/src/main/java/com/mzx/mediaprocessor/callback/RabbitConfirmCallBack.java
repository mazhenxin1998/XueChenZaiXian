package com.mzx.mediaprocessor.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ZhenXinMa
 * @date 2020/5/19 21:47
 */
@Component
public class RabbitConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String errorCode) {

        System.out.println(correlationData.toString());

        if(ack){

            System.out.println("Confirm机制:   信息正常处理.." +  errorCode);
        }else{
            System.out.println("Confirm机制:   信息错误处理.."+errorCode);
        }
    }

}
