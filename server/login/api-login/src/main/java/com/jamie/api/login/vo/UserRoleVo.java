package com.jamie.api.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户和角色关联VO
 */
@Getter
@Setter
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = -6901658933422552291L;

    private Integer userId;
    private String userName;

    private Integer roleId;
    private String role;
}
