package com.jamie.web.a.controller;

import com.jamie.api.a.api.Aapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/a")
public class AController {

    @Autowired
    private Aapi aapi;

    @RequestMapping("/data")
    @ResponseBody
    public String dataA(){
        return aapi.getData();
    }

    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    @RequestMapping("/ab")
    @ResponseBody
    public int insertAB(@RequestParam("m") String msg){
        return aapi.insertAandB(msg);
    }

    @RequestMapping("/abc")
    @ResponseBody
    public int insertABC(@RequestParam("m") String msg) {
        return aapi.insertABC(msg);
    }

}
