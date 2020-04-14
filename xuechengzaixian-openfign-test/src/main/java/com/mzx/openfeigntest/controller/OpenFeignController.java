package com.mzx.openfeigntest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 20:58
 */
@RestController
@RequestMapping(value = "/open/feign")
public class OpenFeignController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/t")
    public String t1(){
        return "端口测试:   "+port;
    }

}
