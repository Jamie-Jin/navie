package com.jamie.api.b.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BEntity implements Serializable {
    private static final long serialVersionUID = 3855010438355656435L;

    private Integer id;
    private String msg;
    private Date createTime;
    private Date updateTime;
}
