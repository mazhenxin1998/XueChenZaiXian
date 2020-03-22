package com.mzx.server.cmsclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 15:18
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/t1")
    public String t1(){
        return "搭建成功";
    }

}
