package com.mzx.api.course;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseMarket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/4/3 21:28
 */
@Api(value = "课程营销信息接口")
public interface ICourseMarketControllerApi {

    @ApiOperation(value = "根据课程ID查询该课程的营销信息")
    QueryResponseResult get(String id);

    @ApiOperation(value = "更新课程的营销信息")
    ResponseResult update(CourseMarket courseMarket);



}
