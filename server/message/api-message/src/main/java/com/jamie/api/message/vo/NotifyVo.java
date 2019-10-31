package com.jamie.api.message.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotifyVo implements Serializable {
    private static final long serialVersionUID = -5191093587266696504L;

    private String correlationId; //消息唯一标识
    private String routingKey;    //路由Key
    private String body;          //消息体，JSON字符串
    private Object message;       //消息，对象
}
