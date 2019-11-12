package com.jamie.api.resource.api;

import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(value = "service-resource", url = "${service.resource}")
@FeignClient(value = "service-resource", url = "http://localhost:1007")
public interface ResourceApi {



}
