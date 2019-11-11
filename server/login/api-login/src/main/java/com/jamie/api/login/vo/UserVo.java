package com.jamie.api.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserVo implements Serializable {
    private static final long serialVersionUID = -1064779653605011110L;

    private Integer id;
    private String account;
    private String name;
    private String password;
    private int enable;
    private Date createTime;
    private Date updateTime;

    private String role;    //角色英文名
    private String roleCn;  //角色中文名
}
