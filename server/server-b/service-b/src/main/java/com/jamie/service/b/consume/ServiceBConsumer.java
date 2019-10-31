package com.jamie.service.b.consume;

import com.alibaba.fastjson.JSON;
import com.jamie.api.message.vo.NotifyVo;
import com.jamie.service.b.biz.BMessageBiz;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ServiceBConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ServiceBConsumer.class);

    @Autowired
    private BMessageBiz bMessageBiz;

    // TODO 硬编码，看能否解决
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "rabbit_queue", durable = "true"),
                    exchange = @Exchange(value = "rabbit_exchange", durable = "true", type = "topic",
                               ignoreDeclarationExceptions = "true"),
                    key = "com.jamie.#"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception{
        logger.info("数据模块B接收到的消息：" + JSON.toJSONString(message.getPayload()));

        // 消费者手工确认, 向RabbitMQ发送消息投递成功确认
        // deliveryTag是RabbitMQ向消费端投递消息时附送的，从1开始递增，消费端的消息确认就是将deliveryTag返回给RabbitMQ
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        // 接收到的消息
        NotifyVo notifyVo = (NotifyVo) message.getPayload();

        // 检查消息是否已被消费, rows不为0，说明消息已被消费
        int rows = bMessageBiz.checkConsume(notifyVo);
        if (rows > 0){
            // 第二参数 requeue:是否重返RabbitMQ队列
            channel.basicReject(deliveryTag, false);
            logger.info("ID为：{}的消息已被消费过，不能重复消费", notifyVo.getCorrelationId());
            return;
        }

        // 新建一条消费日志
        bMessageBiz.insertConsumeLog(notifyVo);

        try {
            // TODO 执行模块B的操作
            logger.info("执行模块B操作");

            // 更新，消息消费成功
            bMessageBiz.consumeSuccess(notifyVo);

            // 第二参数 multiple：是否批量确认消息，为true时，会将比当前deliveryTag小的消息都确认了
            channel.basicAck(deliveryTag, false);
        } catch (Exception e){
            // 在控制台打印错误信息
            e.printStackTrace();

            // 更新，消息消费失败
            bMessageBiz.consumeFail(notifyVo);

            // 告知RabbitMQ，消息消费失败
            // 第三参数 requeue：当前消息是否重返RabbitMQ队列（该消息会被重新投递给消费端）
            channel.basicNack(deliveryTag, false, false);
        }
    }
}
