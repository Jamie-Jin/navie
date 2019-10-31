package com.jamie.service.message.biz;

import com.alibaba.fastjson.JSON;
import com.jamie.api.message.vo.NotifyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MessageBiz2 implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private static final Logger logger = LoggerFactory.getLogger(MessageBiz2.class);

    @Value("${rabbit.exchange}")
    private String exchange;


    // 通过构造器注入RabbitTemplate
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageBiz2(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 异步监听消息是否送到exchange，生产者端消息确认机制
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            logger.info("CorrelationData:" + JSON.toJSONString(correlationData));
            logger.info("消息发送到Exchange成功，correlationId：" + correlationData.getId());

        } else {
            logger.error("消息发送到Exchange失败，correlationId：" + correlationData.getId());
        }
    }

    /**
     * 异步监听消息是否送达queue
     * 触发此回调方法的条件 1）消息已到达exchange 2）消息无法到达queue
     * @param message       返回的消息
     * @param replyCode     回复code
     * @param replyText     回复text
     * @param exchange      交换器
     * @param routingKey    路由键
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        // 消息唯一标识
        String correlationId = message.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();

        logger.error("没有找到相应队列，消息投递失败, ID: {}, replyCode: {}, replyText: {}, exchange: {}, routingKey: {}",
                correlationId, replyCode, replyText, exchange, routingKey);
    }

    /**
     * 向RabbitMQ发送消息
     * RabbitMQ生产者默认实现消息持久化，无需配置
     * @param notifyVo
     */
    public void send(NotifyVo notifyVo){
        // 消息唯一标识
        CorrelationData correlationData = new CorrelationData(notifyVo.getCorrelationId());

        // 发送消息
        rabbitTemplate.convertAndSend(exchange, notifyVo.getRoutingKey(), notifyVo, correlationData);
    }

}
