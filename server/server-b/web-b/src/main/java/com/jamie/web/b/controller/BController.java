package com.jamie.web.b.controller;

import com.jamie.api.b.api.Bapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class BController {

    @Autowired
    private Bapi bapi;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return bapi.getLatestData();
    }

}
