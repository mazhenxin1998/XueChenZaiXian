package com.mzx.api.course;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.course.ext.CourseInfo;
import io.swagger.annotations.Api;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 22:05
 */
@Api(value = "课程管理接口")
public interface ICourseControllerApi {

    /**
     * 分页查询出当前用户(老师或者用户)对用的课程.
     * @return
     */
    QueryResponseResult get();




}
