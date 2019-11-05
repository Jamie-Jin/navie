package com.jamie.service.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色与菜单关联实体类
 */
@Getter
@Setter
public class RoleMenuEntity implements Serializable {
    private static final long serialVersionUID = -8907075742656400133L;

    private Integer id;
    private Integer roleId;
    private Integer menuId;

    private Date createTime;
    private Date updateTime;
}
