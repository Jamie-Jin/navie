package com.jamie.api.c.api;

import com.jamie.api.c.url.Urls;
import com.jamie.api.c.vo.CVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-c", url = "${service.c}")
public interface CApi {

    @PostMapping(Urls.insertC)
    void insertC(@RequestBody CVo cVo);

}
