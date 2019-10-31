package com.jamie.api.message.api;

import com.jamie.api.message.urls.Urls;
import com.jamie.api.message.vo.NotifyVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-message", url = "${service.message}")
public interface MessageApi {

    @PostMapping(Urls.sendMessage)
    void send(@RequestBody NotifyVo notifyVo) throws Exception;

}
