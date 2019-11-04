package com.jamie.service.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 4718909262381474889L;

    private Integer id;
    private String role;
    private Date createTime;
    private Date updateTime;
}
