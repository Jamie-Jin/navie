package com.jamie.service.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = -4808126296960506571L;

    private Integer userId;
    private String userName;

    private Integer roleId;
    private String role;
}
