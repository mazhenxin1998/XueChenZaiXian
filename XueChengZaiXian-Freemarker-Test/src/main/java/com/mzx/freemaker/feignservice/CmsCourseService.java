package com.mzx.freemaker.feignservice;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.course.CourseView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhenXinMa
 * @date 2020/4/12 19:37
 */
@Component
@FeignClient(value = "xuechengzaixina-server-manager-course-31200")
@RequestMapping(value = "/course")
public interface CmsCourseService {


    @GetMapping(value = "/courseView/{id}")
    CourseView courseView(@PathVariable(value = "id") String id);


}
