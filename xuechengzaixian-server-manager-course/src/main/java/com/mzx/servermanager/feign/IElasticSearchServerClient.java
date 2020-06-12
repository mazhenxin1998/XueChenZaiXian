package com.mzx.servermanager.feign;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhenXinMa
 * @date 2020/4/30 8:59
 */
@Component
@FeignClient(value = "xuechengzaixian-server-search-es")
@RequestMapping(value = "/search")
public interface IElasticSearchServerClient {

    @PostMapping(value = "/course/add")
    ResponseResult addIndex(CoursePub coursePub);

//    @GetMapping(value = "/search/high")
//    QueryResponseResult search(String keyword, String mt, String st, String grade, Double price_min,
//                               Double price_max, String sort, String filter);

    @PostMapping(value = "/search/high")
    QueryResponseResult search(CourseSearchParam param);


}
