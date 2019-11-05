package com.jamie.service.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单实体类
 */
@Getter
@Setter
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 7549776133905528661L;

    private Integer id;
    private String path;        //访问路径
    private Date createTime;
    private Date updateTime;
}
