package com.mzx.learning.controller;

import com.mzx.api.learning.CourseLearningControllerApi;
import com.mzx.framework.model.learning.response.GetMediaResult;
import com.mzx.learning.service.ICourseLearningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 22:33
 */
@RestController
@RequestMapping(value = "/learning")
public class CourseLearningController implements CourseLearningControllerApi {

    @Resource
    private ICourseLearningService iCourseLearningService;


    @Override
    @GetMapping(value = "/getmedia/{courseID}/{teachPlanID}")
    public GetMediaResult getMedia(@PathVariable(value = "courseID", required = false) String courseID,
                                   @PathVariable(value = "teachPlanID") String teachPlanID) {

        return iCourseLearningService.getMedia(courseID, teachPlanID);
    }
}
