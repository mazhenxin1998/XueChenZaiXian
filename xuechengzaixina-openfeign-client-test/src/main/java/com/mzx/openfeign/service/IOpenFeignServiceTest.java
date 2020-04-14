package com.mzx.openfeign.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhenXinMa
 * @date 2020/4/8 21:14
 */
@Component
@FeignClient(value = "xuechengzaixian-openfeign-test")
@RequestMapping(value = "/open/feign")
public interface IOpenFeignServiceTest {

    @GetMapping(value = "/t")
    String t1();

}
