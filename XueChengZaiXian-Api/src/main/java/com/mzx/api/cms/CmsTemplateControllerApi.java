package com.mzx.api.cms;

import com.mzx.common.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:03
 */
@Api(value = "页面模板查询API" ,description = "查询")
public interface CmsTemplateControllerApi {

    /**
     *  查询所有的模板
     * @return  查询所有的模板
     */
    @ApiOperation(value = "查询所有的模板")
    public QueryResponseResult get();

}
