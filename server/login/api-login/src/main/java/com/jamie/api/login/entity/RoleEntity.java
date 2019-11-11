package com.jamie.api.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色实体类
 */
@Getter
@Setter
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -8616117407179493379L;

    private Integer id;
    private String role;
    private String roleCn;
    private Date createTime;
    private Date updateTime;
}
