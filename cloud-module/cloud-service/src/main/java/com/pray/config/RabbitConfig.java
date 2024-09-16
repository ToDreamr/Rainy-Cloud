package com.pray.config;

import com.pray.constants.RabbitMqConstants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * RabbitConfig 配置RabbitMQ的消息转换适配器
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/30 15:32
 */
@Configuration
public class RabbitConfig {
    //工作队列模式：
    @Resource
    private CachingConnectionFactory connectionFactory;
    @Bean
    @Order(-1)
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(){
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setPrefetchCount(1);
        return containerFactory;
    }
    @Resource
    private SimpleRabbitListenerContainerFactory factory;
    @Bean(name = "loacal-template")
    @Order(0)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(factory.createListenerContainer().getConnectionFactory());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());//Json消息转换器
        rabbitTemplate.setReceiveTimeout(RabbitMqConstants.TIMEOUT);
        rabbitTemplate.setReplyTimeout(RabbitMqConstants.TIMEOUT);
        return rabbitTemplate;
    }
}
