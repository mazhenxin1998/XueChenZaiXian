package com.mzx.search.controller;

import com.mzx.api.search.ElasticSearchControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;
import com.mzx.search.service.IElasticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 15:39
 */
@Slf4j
@RestController
@RequestMapping(value = "/es")
public class ElasticSearchController implements ElasticSearchControllerApi {

    @Resource
    private IElasticService service;

    @Override
    @PostMapping(value = "/add")
    public ResponseResult addIndex(@RequestBody CoursePub coursePub) {

        log.info(coursePub.toString());
        System.out.println(1);
        return service.addIndex(coursePub);
    }

    @Override
    @PostMapping(value = "/search/high")
    public QueryResponseResult search(@RequestBody CourseSearchParam param) {

        System.out.println(1);
        return service.searchKeyWord(param);
    }

    @Override
    @GetMapping(value = "/delete/{id}")
    public ResponseResult delete(@PathVariable(value = "id",required = true) String id) {
        return null;
    }

    @Override
    @GetMapping(value = "/list/{page}/{size}")
    public QueryResponseResult list(@PathVariable(value = "page", required = true) int page,
                                    @PathVariable(value = "size", required = true) int size,
                                    CourseSearchParam param) {
        return service.listByPageSizeAndHighLight(page,size,param);
    }
}
