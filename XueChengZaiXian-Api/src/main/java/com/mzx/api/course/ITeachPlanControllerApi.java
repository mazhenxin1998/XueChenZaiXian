package com.mzx.api.course;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.course.TeachPlan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 14:17
 */
@Api(value = "课程管理接口")
public interface ITeachPlanControllerApi {

    @ApiOperation(value = "根据课程ID查看其具体情况")
    @ApiImplicitParam(name = "courseID", value = "要查询的课程号ID", required = true, paramType = "path", dataType = "String")
    QueryResponseResult getByCourseID(String courseID);

    @ApiOperation(value = "添加课程计划")
    ResponseResult add(TeachPlan teachPlan);

    @ApiOperation(value = "删除节点")
    @ApiImplicitParam(name = "id", value = "要删除节点的ID", required = true, paramType = "path", dataType = "String")
    ResponseResult delete(String id);

    ResponseResult update();

}
