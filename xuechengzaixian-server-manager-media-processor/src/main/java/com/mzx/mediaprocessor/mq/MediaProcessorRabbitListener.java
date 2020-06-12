package com.mzx.mediaprocessor.mq;

import com.rabbitmq.client.Channel;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/5/18 17:37
 */
public interface MediaProcessorRabbitListener {

    /**
     * 监听MQ中的消息队列.
     * 收到消息后就处理消息中的视屏.
     * @Async 异步注解相当于表示多个消费者在监听同一个生产者  提高了效率
     */
    void listener(@Payload String message, Channel channel, @Headers Map<String, Object> headers);

}
