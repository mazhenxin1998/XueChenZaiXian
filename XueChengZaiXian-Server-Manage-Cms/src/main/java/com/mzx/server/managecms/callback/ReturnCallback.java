package com.mzx.server.managecms.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 21:08
 *
 *      return返回机制： 当投递的消息找不到指定的Exchange或者根据routeKing找不到指定的队列
 *      这个时候我们需要监听不可达的消息，如果消息不可达，那么就触发该callback
 *
 */
@Component
public class ReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.err.println("ERROR Code"+replyCode+"    ReplyText:  "+replyText+"    exchange:  "+exchange+"    routingKey   "+routingKey);
        System.out.println("Message:    "+message);
    }
}
