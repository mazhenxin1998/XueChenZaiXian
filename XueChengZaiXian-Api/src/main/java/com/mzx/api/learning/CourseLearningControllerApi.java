package com.mzx.api.learning;

import com.mzx.framework.model.learning.response.GetMediaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 22:27
 */
@Api(value = "课程学习接口查询")
public interface CourseLearningControllerApi {

    /**
     * 获取courseID对应的课程的teachPlanID小节的视屏的URL地址.
     * @param courseID
     * @param teachPlanID
     * @return
     */
    @ApiOperation(value = "通过课程ID和计划ID获取媒资信息.")
    GetMediaResult getMedia(String courseID,String teachPlanID);

}
