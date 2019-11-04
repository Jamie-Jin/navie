package com.jamie.api.a.api;

import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.urls.Urls;
import com.jamie.api.a.vo.AVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-a", url = "${service.a}")
public interface Aapi {

    @PostMapping(Urls.insertA)
    int insertA(@RequestBody AVo aVo);

    @PostMapping(Urls.messageAToB)
    void messageAToB(@RequestBody AVo aVo);

    @PostMapping(Urls.getData)
    String getData();

    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    @PostMapping(Urls.insertAandB)
    int insertAandB(@RequestBody String msg);

    @PostMapping(Urls.insertABC)
    int insertABC(@RequestBody String msg);

}
