package com.mzx.learning.controller;

import com.mzx.framework.model.course.TeachPlanMediaPub;
import com.mzx.framework.model.learning.XcLearningCourse;
import com.mzx.learning.dao.IXcLearningCourseDao;
import com.mzx.learning.service.feign.ISearchServerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 17:12
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private IXcLearningCourseDao xcLearningCourseDao;

    @Resource
    private ISearchServerClient searchServerClient;

    @GetMapping(value = "/t1")
    public Object t1(){

        List<XcLearningCourse> xcLearningCourses = xcLearningCourseDao.listAll();
        return xcLearningCourses;
    }

    @GetMapping(value = "/t2")
    public Object t2(){

        TeachPlanMediaPub media = searchServerClient.getMedia("402881e6642027980164202ab9270001");
        return media;
    }



}
