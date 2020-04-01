package com.mzx.api.course;

import com.mzx.common.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 20:06
 */
@Api(value = "数据字典查询接口")
public interface ISysDictionaryControllerApi {

    @ApiOperation("根据DName查询出符合条件的数据字典")
    @ApiImplicitParam(name = "DName",value = "要查询的DName",required = true,dataType = "String",paramType = "path")
    QueryResponseResult getByDName(String DName);

    @ApiOperation(value = "根据DType查询出符合条件的数据字典")
    QueryResponseResult getByDType(String DType);


}
