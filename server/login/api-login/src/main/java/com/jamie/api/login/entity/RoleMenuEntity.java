package com.jamie.api.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单和角色关联实体类
 */
@Getter
@Setter
public class RoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 8924217847865188670L;

    private Integer id;
    private Integer roleId;
    private Integer menuId;

    private Date createTime;
    private Date updateTime;
}
