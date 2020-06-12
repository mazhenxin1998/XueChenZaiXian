package com.mzx.media.controller;

import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.media.dao.ITeachPlanMediaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/5/25 23:24
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private ITeachPlanMediaDao iTeachPlanMediaDao;

    @GetMapping(value = "/t1")
    public Object t1() {

        List<TeachPlanMedia> teachPlanMedia = iTeachPlanMediaDao.listAll();
        return teachPlanMedia;
    }


}
