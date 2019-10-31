package com.jamie.api.a.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AProducerLogEntity implements Serializable {
    private static final long serialVersionUID = -8139897341074393458L;

    private Integer id;
    private String correlationId;   //消息唯一标识
    private String routingKey;      //消息路由键值
    private int status;
    private Date createTime;
    private Date updateTime;
    private String body;           //消息体（JSON字符串）
}
