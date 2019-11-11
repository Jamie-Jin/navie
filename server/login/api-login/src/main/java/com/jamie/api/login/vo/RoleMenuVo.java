package com.jamie.api.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单与角色关联vo，一个菜单可能对应多个权限
 */
@Getter
@Setter
public class RoleMenuVo implements Serializable {
    private static final long serialVersionUID = -5986805669294673074L;

    private Integer menuId;
    private String menu;            //菜单

    private List<RoleVo> roleVos;   //权限s
}
