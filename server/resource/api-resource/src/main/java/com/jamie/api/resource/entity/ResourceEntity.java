package com.jamie.api.resource.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ResourceEntity implements Serializable {
    private static final long serialVersionUID = -6599601723533973476L;

    private Integer id;
    private String name;
    private int count;
    private BigDecimal price;
    private int enable;
    private Date createTime;
    private Date updateTime;
}
