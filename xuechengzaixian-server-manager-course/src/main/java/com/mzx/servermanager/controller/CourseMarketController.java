package com.mzx.servermanager.controller;

import com.mzx.api.course.ICourseMarketControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseMarket;
import com.mzx.servermanager.service.ICourseMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/3 21:31
 */
@RestController
@RequestMapping(value = "/course/market")
@Slf4j
public class CourseMarketController implements ICourseMarketControllerApi {

    @Resource
    private ICourseMarketService courseMarketService;

    @Override
    @GetMapping(value = "/get/{id}")
    public QueryResponseResult get(@PathVariable String id) {
        log.info("----------------------CourseMarketController 中的Get 方法执行了ID"+id);
        return courseMarketService.get(id);
    }

    @Override
    @PostMapping(value = "/update")
    public ResponseResult update(@RequestBody CourseMarket courseMarket) {
        log.info("--------------------CourseMarketController UPDATE "+courseMarket);
        return courseMarketService.update(courseMarket);
    }
}
