package com.mzx.media.mq;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 10:26
 */
public interface MediaRabbitMessageProducer {

    /**
     * MQ消息发送.
     *
     * @param message 要发送的Map. 最后要转换成JSON格式.
     * @param headers 发送消息的请求头.
     */
    void sendMessage(Map<String, Object> message, Map<String, Object> headers, String mediaFileID);

}
