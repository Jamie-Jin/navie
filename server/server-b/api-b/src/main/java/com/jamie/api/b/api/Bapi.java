package com.jamie.api.b.api;

import com.jamie.api.b.entity.BEntity;
import com.jamie.api.b.urls.Urls;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-b", url = "${service.b}")
public interface Bapi {

    @PostMapping(Urls.insertB)
    int insertB(@RequestBody BEntity bEntity);

    // 获取最新的数据
    @PostMapping(Urls.getLatestData)
    String getLatestData();
}
