package com.mzx.media.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 10:18
 */
@Component
public class RabbitReturnCallback implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

        System.out.println("return机制触发...");
        System.out.println("第一个参数 " + message);
        System.out.println("第二个参数 " + i);
        System.out.println("第三个参数 " + s);
        System.out.println("第四个参数 " + s1);
        System.out.println("第五个参数 " + s2);
        /*对异常的消息进行处理.*/

    }

}
