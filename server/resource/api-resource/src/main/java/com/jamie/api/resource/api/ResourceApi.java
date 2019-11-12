package com.jamie.api.resource.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-resource", url = "${service.resource}")
public interface ResourceApi {



}
