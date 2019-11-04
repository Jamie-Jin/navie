package com.jamie.service.c.rest;

import com.jamie.api.c.api.CApi;
import com.jamie.api.c.vo.CVo;
import com.jamie.redis.api.RedisApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRestTest {

    @Autowired
    private CApi cApi;

    @Autowired
    private RedisApi redisApi;

    @Test
    public void insertC() {
        CVo cVo = new CVo();
        cVo.setKey("key1");
        cVo.setVal("redis插入测试");
        cVo.setExpireTime(60);
        cApi.insertC(cVo);
    }

    @Test
    public void test(){
        redisApi.expire("key1", 1);
    }
}