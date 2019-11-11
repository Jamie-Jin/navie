package com.jamie.api.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Getter
@Setter
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -7892014740179695707L;

    private Integer id;
    private String account;
    private String name;
    private String password;
    private int enable;
    private Date createTime;
    private Date updateTime;
}
