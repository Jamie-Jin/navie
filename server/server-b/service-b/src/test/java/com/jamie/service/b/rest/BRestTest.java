package com.jamie.service.b.rest;

import com.jamie.api.b.api.Bapi;
import com.jamie.api.b.entity.BEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BRestTest {

    @Autowired
    private Bapi bapi;

    @Test
    public void insertB() {
        BEntity bEntity = new BEntity();
        bEntity.setMsg("数据模块B-插入测试");
        bapi.insertB(bEntity);
    }
}