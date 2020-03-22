package com.mzx.server.managecms.mqproducer;

import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/3/2 17:14
 * @since 1.8
 */
public interface IMessageProduceSender {

    /**
     *
     * 概要描述：自行封装的MQ发送机制.
     *
     * 详细描述：通过将Map转换为JSON进行将消息发送.
     *
     * @param message 要发送的消息 格式为Map  但是将会转换成JSON
     * @param properties  消息的头部信息 封装了该消息的一些协议
     *
     *  练习Javadoc 第一段：概要描述  第二段详细描述
     */
    void sendMessage(Map<String,Object> message,Map<String,Object> properties);

}
