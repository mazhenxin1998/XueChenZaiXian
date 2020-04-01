package com.mzx.api.course;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 12:01
 */
@Api(value = "课程分类接口")
public interface ICategoryControllerApi {

    @ApiOperation(value = "查询所有的课程分类")
    CategoryNode get();

}
