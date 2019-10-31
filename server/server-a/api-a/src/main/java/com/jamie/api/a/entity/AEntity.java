package com.jamie.api.a.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AEntity implements Serializable {
    private static final long serialVersionUID = -806057317069600594L;

    private Integer id;
    private String msg;
    private Date createTime;
    private Date updateTime;
}
