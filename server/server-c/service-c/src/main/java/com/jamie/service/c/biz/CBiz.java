package com.jamie.service.c.biz;

import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.google.common.collect.Sets;
import com.jamie.api.c.vo.CVo;
import com.jamie.redis.api.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CBiz {

    @Autowired
    private RedisApi redisApi;

    private ConcurrentHashMap<String, Set<String>> ids = new ConcurrentHashMap<>();

    @TccTransaction(cancelMethod = "cancel", confirmMethod = "confirm")
    public void insertC(CVo cVo){
        //Sets.newHashSet: com.google.common.collect，Guava依赖
        ids.putIfAbsent(TracingContext.tracing().groupId(), Sets.newHashSet(cVo.getKey()));
        ids.get(TracingContext.tracing().groupId()).add(cVo.getKey());

        redisApi.set(cVo.getKey(), cVo.getVal(), cVo.getExpireTime());

        throw new RuntimeException("跨数据源分布式事务测试");
    }

    // 事务确认提交
    public void confirm(CVo cVo){
        ids.get(TracingContext.tracing().groupId()).forEach(id -> {
            ids.get(TracingContext.tracing().groupId()).remove(id);
        });
    }

    // 事务回滚
    public void cancel(CVo cVo){
        ids.get(TracingContext.tracing().groupId()).forEach(id -> {
            redisApi.expire(id, 1);
        });
    }
}
