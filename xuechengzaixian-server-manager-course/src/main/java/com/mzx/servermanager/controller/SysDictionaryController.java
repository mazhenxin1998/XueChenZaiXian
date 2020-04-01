package com.mzx.servermanager.controller;

import com.mzx.api.course.ISysDictionaryControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.servermanager.service.ISysDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 20:09
 */
@RestController
@RequestMapping(value = "/course/dictionary")
@Slf4j
public class SysDictionaryController implements ISysDictionaryControllerApi {

    @Resource
    private ISysDictionaryService dictionaryService;

    @Override
    @GetMapping(value = "/get/{DName}")
    public QueryResponseResult getByDName(@PathVariable(value = "DName") String DName) {
        return dictionaryService.getByDName(DName);
    }

    @Override
    @GetMapping(value = "/get/type/{type}")
    public QueryResponseResult getByDType(@PathVariable(value = "type") String type) {
        log.info("-------------------------------SysDictionaryController 中 getByDType 执行了 Type :" +type);
        return dictionaryService.getByType(type);
    }
}
