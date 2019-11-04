package com.jamie.service.c.biz;

import com.jamie.api.c.vo.CVo;
import com.jamie.redis.api.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CBiz {

    @Autowired
    private RedisApi redisApi;

    public void insertC(CVo cVo){
        redisApi.set(cVo.getKey(), cVo.getVal(), cVo.getExpireTime());
    }
}
