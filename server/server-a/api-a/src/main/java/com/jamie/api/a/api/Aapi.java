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

    // 往模块B发送消息
    @PostMapping(Urls.sendMsgToB)
    void messageAToB(@RequestBody AVo aVo);

    // 获取最新的数据
    @PostMapping(Urls.getLatestData)
    String getLatestData();

    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    @PostMapping(Urls.insertAB)
    int insertAB(@RequestBody String msg);

    // 同一个事务中，在模块ABC中都插入数据，测试跨数据源的TX-LCN事务
    @PostMapping(Urls.insertABC)
    int insertABC(@RequestBody String msg);

    // 在同一个事务中，先在A插入一条数据，然后查询这条数据，然后B得到这条数据后也将插入数据库中
    @PostMapping(Urls.insertAFirstThenB)
    int insertAFirstThenB(@RequestBody String msg);



}
