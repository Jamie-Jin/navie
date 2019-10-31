package com.jamie.service.message.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * RabbitMQ配置
 */
@Configuration
public class RabbitConfig {

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.queue}")
    private String queue;

    @Value("${rabbit.routingKey}")
    private String routingKey;

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    // TODO 为何此处是SCOPE_PROTOTYPE(每次获取都新建对象)
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(cachingConnectionFactory);
    }

    /**
     * 设置交换器名称
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        // durable: 交换器持久化
        // autoDelete：若为true，当没有队列绑定到交换器时，自动删除该交换器
        return new TopicExchange(exchange, true, false);
    }

    /**
     * 设置队列
     * @return
     */
    @Bean
    public Queue queue(){
        // 开启队列持久化
        return new Queue(queue, true);
    }

    /**
     * 设置交换器与队列的绑定关系, 交换器通过routingKey绑定到队列上
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routingKey);
    }

}
