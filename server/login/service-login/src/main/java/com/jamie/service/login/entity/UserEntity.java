package com.jamie.service.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -1488405024013135279L;

    private Integer id;
    private String account;
    private String name;
    private String password;
    private int enable;
    private Date createTime;
    private Date updateTime;
}
