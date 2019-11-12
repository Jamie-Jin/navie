package com.jamie.web.resource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    // 资源上传页
    @RequestMapping("/upload")
    public String upload(){
        return "resource/upload";
    }

    // 资源上传逻辑
    // 注意：接收上传文件参数的命名必须和前端页面的input name一致
    @PostMapping("/upload/excel")
    @ResponseBody
    public String uploadExcel(@RequestParam("upload-excel") MultipartFile multipartFile, HttpServletResponse response){
        return "";
    }

}
