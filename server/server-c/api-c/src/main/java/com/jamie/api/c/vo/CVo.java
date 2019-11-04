package com.jamie.api.c.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 存放redis操作需要的数据
 */
@Getter
@Setter
public class CVo implements Serializable {
    private static final long serialVersionUID = 6467750194770633957L;

    private String key;
    private Object val;
    private long expireTime;
    private TimeUnit timeUnit;
}
