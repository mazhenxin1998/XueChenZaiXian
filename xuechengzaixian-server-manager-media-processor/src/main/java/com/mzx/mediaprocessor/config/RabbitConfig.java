package com.mzx.mediaprocessor.config;



import com.mzx.mediaprocessor.callback.RabbitConfirmCallBack;
import com.mzx.mediaprocessor.callback.RabbitReturnCallBack;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhenXinMa
 * @date 2020/5/18 18:39
 */
@Configuration
public class RabbitConfig {

    /*
    * 可以在这里配置RabbitTemplate 进行消息确认机制.
    * 也可以配置Return机制*/

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){

        System.out.println("初始化RabbitMQ方法执行了...");
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        RabbitConfirmCallBack rabbitConfirmCallBack = new RabbitConfirmCallBack();
        RabbitReturnCallBack rabbitReturnCallBack = new RabbitReturnCallBack();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(rabbitConfirmCallBack);
        rabbitTemplate.setReturnCallback(rabbitReturnCallBack);
        return rabbitTemplate;
    }



    /**
     * 配置多个线程监听同一个生产者.
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
