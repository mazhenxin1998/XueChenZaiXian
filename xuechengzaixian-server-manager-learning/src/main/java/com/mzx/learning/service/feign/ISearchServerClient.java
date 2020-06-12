package com.mzx.learning.service.feign;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.course.TeachPlanMediaPub;
import com.mzx.framework.model.search.CourseSearchParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 22:37
 */
@Component
@FeignClient(value = "${xuechengzaixian.search.server.name}")
@RequestMapping(value = "/search")
public interface ISearchServerClient {

    @PostMapping(value = "/course/add")
    ResponseResult addIndex(@RequestBody CoursePub coursePub);

    @PostMapping(value = "/search/high")
    QueryResponseResult search(@RequestBody CourseSearchParam param);

    @GetMapping(value = "/delete/{id}")
    ResponseResult delete(@PathVariable(value = "id", required = true) String id);

    @GetMapping(value = "/course/list/{page}/{size}")
    QueryResponseResult list(@PathVariable(value = "page", required = true) int page,
                             @PathVariable(value = "size", required = true) int size,
                             CourseSearchParam param);

    @GetMapping(value = "/course/getall/{id}")
    Map<String, CoursePub> getAll(@PathVariable(value = "id") String id);

    @GetMapping(value = "get/media/{teachPlanID}")
    TeachPlanMediaPub getMedia(@PathVariable(value = "teachPlanID") String teachPlanID);

}
