package com.jamie.service.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserRoleEntity implements Serializable {
    private static final long serialVersionUID = 4092562020137962318L;

    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Date createTime;
    private Date updateTime;
}
