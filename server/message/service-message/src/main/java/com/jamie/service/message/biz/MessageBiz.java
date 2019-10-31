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

import java.util.UUID;

@Service
public class MessageBiz {
    private static final Logger logger = LoggerFactory.getLogger(MessageBiz.class);

    // RabbitMQ交换器
    @Value("${rabbit.exchange}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息成功到达消费者
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            logger.info("消息唯一标识correlationData：" + correlationData);
            logger.info("ACK标志：" + ack);
            if (!ack){
                logger.error("消息消费失败");
            } else {
                // TODO 消息消费成功，更新日志表
            }
        }
    };

    /**
     * 消息发送失败将会触发该方法
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        }
    };

    /**
     * 发送消息
     * @throws Exception
     */
    public void send(NotifyVo notifyVo) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        logger.info("生产者发送的消息：" + JSON.toJSONString(notifyVo.getMessage()));

        // 作为消息唯一标识
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 发送消息
        rabbitTemplate.convertAndSend(exchange, notifyVo.getRoutingKey(), notifyVo, correlationData);
    }
}
