package com.mzx.api.cms;


import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:25
 */
@Api(value = "页面配置接口：cms-config")
public interface CmsConfigControllerApi {

    /**
     * @param id  根据id查询CmsConfig
     * @return
     */
    @ApiOperation(value = "根据ID查询cms-config")
    @ApiImplicitParam(name = "id",value = "查询ID",required = true,paramType = "path",dataType = "String")
    CmsConfig get(String id);

    @ApiOperation(value = "查询所有cmsConfig")
    QueryResponseResult get();

    @ApiOperation(value = "查询所有的modelValue")
    QueryResponseResult getModelValues();

    @ApiOperation(value = "增加新的CmsConfig")
    ResponseResult add(CmsConfig cmsConfig);

    @ApiOperation(value = "根据ID删除CmsConfig")
    ResponseResult delete(String id);

    @ApiOperation(value = "更新CmsConfig")
    ResponseResult update(String id,CmsConfig cmsConfig);

}
