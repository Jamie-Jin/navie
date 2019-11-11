package com.jamie.api.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户与角色关联实体类
 */
@Getter
@Setter
public class UserRoleEntity implements Serializable {
    private static final long serialVersionUID = 8182739039814554177L;

    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Date createTime;
    private Date updateTime;
}
