package com.jamie.api.message.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MessageEntity implements Serializable {
    private static final long serialVersionUID = -1041525713493549339L;

    private Integer id;
    private String correlationId;  //消息唯一标识
    private Date createTime;
    private Date updateTime;
    private int status;         //消费状态 0：未消费 1：已消费 -1：消费失败
    private String exchangeRemark;      //投递到exchange的情况
    private int exchangeStatus;
    private String consumeRemark;
    private int consumeStatus;
}
