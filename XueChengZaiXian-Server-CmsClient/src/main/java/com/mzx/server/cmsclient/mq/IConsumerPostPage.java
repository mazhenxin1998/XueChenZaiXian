package com.mzx.server.cmsclient.mq;

import com.rabbitmq.client.Channel;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/3/2 17:33
 * @since 1.8
 */
public interface IConsumerPostPage  {



    void postPage(@Payload String message, Channel channel, @Headers Map<String,Object> headers);


}
