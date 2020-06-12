package com.mzx.media.config;

import com.mzx.media.callback.RabbitConfirmCallback;
import com.mzx.media.callback.RabbitReturnCallback;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 10:22
 */
@Configuration
public class RabbitConfig {

    @Resource
    private RabbitConfirmCallback rabbitConfirmCallback;

    @Resource
    private RabbitReturnCallback rabbitReturnCallback;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(rabbitReturnCallback);
        rabbitTemplate.setConfirmCallback(rabbitConfirmCallback);

        return rabbitTemplate;
    }



}
