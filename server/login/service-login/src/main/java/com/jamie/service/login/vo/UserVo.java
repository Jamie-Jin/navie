package com.jamie.service.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserVo implements Serializable {
    private static final long serialVersionUID = -6533974015113477700L;

    private Integer id;
    private String account;
    private String name;
    private String password;
    private int enable;
    private Date createTime;
    private Date updateTime;

    private String role;
    private String roleCn;

}
