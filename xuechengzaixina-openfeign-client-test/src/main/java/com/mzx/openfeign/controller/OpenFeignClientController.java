package com.mzx.openfeign.controller;

import com.mzx.openfeign.service.IOpenFeignServiceTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 21:15
 */
@RestController
@RequestMapping(value = "/open/feign/client")
public class OpenFeignClientController {

    @Resource
    private IOpenFeignServiceTest openFeignServiceTest;

    @GetMapping(value = "/t")
    public String t1(){
        return openFeignServiceTest.t1();
    }


}
