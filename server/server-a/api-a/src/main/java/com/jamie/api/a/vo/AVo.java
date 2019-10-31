package com.jamie.api.a.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AVo implements Serializable {
    private static final long serialVersionUID = -7968314743551630397L;

    private Integer id;
    private String msg;
    private Date createTime;
    private Date updateTime;
}
