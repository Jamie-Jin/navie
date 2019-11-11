package com.jamie.api.login.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单（访问路径）实体类
 */
@Getter
@Setter
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = -4202518078631550438L;

    private Integer id;
    private String path;        //访问路径
    private Date createTime;
    private Date updateTime;
}
