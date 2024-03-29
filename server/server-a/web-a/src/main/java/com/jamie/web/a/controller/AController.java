package com.jamie.web.a.controller;

import com.alibaba.fastjson.JSON;
import com.jamie.api.a.api.Aapi;
import com.jamie.api.a.vo.AVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class AController {

    @Autowired
    private Aapi aapi;

    @RequestMapping("/latest-data")
    @ResponseBody
    public String getLatestData(){
        return aapi.getLatestData();
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(AVo aVo){
        return JSON.toJSONString(aapi.getDataBy(aVo));
    }

    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    @RequestMapping("/ab")
    @ResponseBody
    public int insertAB(@RequestParam("m") String msg){
        return aapi.insertAB(msg);
    }

    @RequestMapping("/abc")
    @ResponseBody
    public int insertABC(@RequestParam("m") String msg) {
        return aapi.insertABC(msg);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int update(AVo aVo){
        return aapi.updateA(aVo);
    }

}
