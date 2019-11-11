package com.jamie.api.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 角色VO
 */
@Getter
@Setter
public class RoleVo implements Serializable {
    private static final long serialVersionUID = 1136178776178318037L;

    private Integer id;
    private String role;    //权限英文名
    private String roleCn;  //权限中文名
}
