package com.jamie.service.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RoleMenuVo implements Serializable {
    private static final long serialVersionUID = -3233653773434261322L;

    private Integer menuId;
    private String menu;

    private List<RoleVo> roleVos;
}
