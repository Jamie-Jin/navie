package com.jamie.service.login.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleVo implements Serializable {
    private static final long serialVersionUID = 7216477716193564646L;

    private Integer id;
    private String role;
    private String roleCn;
}
