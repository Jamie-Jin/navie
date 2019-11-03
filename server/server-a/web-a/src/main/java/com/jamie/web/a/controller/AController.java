package com.jamie.web.a.controller;

import com.jamie.api.a.api.Aapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
