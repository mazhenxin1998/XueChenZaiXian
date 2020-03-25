package com.mzx.servermanager.controller;

import com.mzx.api.course.ITeachPlanControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.TeachPlan;
import com.mzx.servermanager.service.ITeachPlanService;
import javafx.scene.chart.ValueAxis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 14:23
 */
@RestController
@RequestMapping(value = "/course/teach/plan")
@Slf4j
public class TeachPlanController implements ITeachPlanControllerApi {

    @Resource
    private ITeachPlanService teachPlanService;


    @Override
    @GetMapping(value = "/get/{courseID}")
    public QueryResponseResult getByCourseID(@PathVariable(value = "courseID") String courseID) {
        log.info("-----------------------Controller调用成功!");
        return teachPlanService.get(courseID);
    }


    @Override
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody TeachPlan teachPlan) {
        return null;
    }


}
