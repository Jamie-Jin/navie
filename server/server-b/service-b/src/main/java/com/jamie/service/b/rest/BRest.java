package com.jamie.service.b.rest;

import com.jamie.api.b.api.Bapi;
import com.jamie.api.b.entity.BEntity;
import com.jamie.api.b.urls.Urls;
import com.jamie.service.b.biz.Bbiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BRest implements Bapi {

    @Autowired
    private Bbiz bbiz;

    @Override
    @PostMapping(Urls.insertB)
    public int insertB(@RequestBody BEntity bEntity) {
        return bbiz.insertB(bEntity);
    }
}
