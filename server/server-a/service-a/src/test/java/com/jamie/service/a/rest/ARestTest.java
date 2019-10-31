package com.jamie.service.a.rest;

import com.jamie.api.a.api.Aapi;
import com.jamie.api.a.vo.AVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARestTest {
    @Autowired
    private Aapi aapi;

    @Test
    public void insertA() {
        AVo aVo = new AVo();
        aVo.setMsg("服务模块A-数据插入测试5");
        aapi.insertA(aVo);
    }

    @Test
    public void test(){
        AVo aVo = new AVo();
        aVo.setMsg("A发送消息给B");
        aapi.messageAToB(aVo);
    }
}