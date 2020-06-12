package com.mzx.mediaprocessor.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/5/19 18:01
 */
@Component
public class RabbitReturnCallBack implements RabbitTemplate.ReturnCallback {

    /**
     * return返回机制.只是对消息生产者有效.
     *
     * @param message 错误信息.
     * @param i       错误代码.
     * @param s
     * @param s1      交换机Exchange.
     * @param s2      路由Key.
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("第一个参数 Message:   " + message);
        System.out.println("第二个参数 i      :   " + i);
        System.out.println("第三个参数 s      :   " + s);
        System.out.println("第四个个参数 s      :   " + s1);
        System.out.println("第五个个参数 s      :   " + s2);
    }
}
