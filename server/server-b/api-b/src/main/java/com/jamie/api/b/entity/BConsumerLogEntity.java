package com.jamie.api.b.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BConsumerLogEntity implements Serializable {
    private static final long serialVersionUID = 7058918712054691389L;

    private Integer id;
    private String correlationId;   //消息唯一标识
    private String routingKey;      //消息路由键
    private int status;             //消息消费状态
    private int consumeCount;       //消息消费次数
    private Date createTime;
    private Date updateTime;
    private String body;            //消息体（JSON）
}
