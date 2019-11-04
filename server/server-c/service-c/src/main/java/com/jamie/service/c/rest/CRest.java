package com.jamie.service.c.rest;

import com.jamie.api.c.api.CApi;
import com.jamie.api.c.url.Urls;
import com.jamie.api.c.vo.CVo;
import com.jamie.service.c.biz.CBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRest implements CApi {

    @Autowired
    private CBiz cBiz;

    @Override
    @PostMapping(Urls.insertC)
    public void insertC(@RequestBody CVo cVo) {
        cBiz.insertC(cVo);
    }

}
