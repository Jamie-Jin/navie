package com.jamie.web.resource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ResourceController {

    @RequestMapping("/resource")
    public String index(){
        return "resource/index";
    }

}
