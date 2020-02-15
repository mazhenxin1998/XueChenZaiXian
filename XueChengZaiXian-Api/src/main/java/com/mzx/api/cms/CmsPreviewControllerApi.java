package com.mzx.api.cms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.naming.Name;

/**
 * @author ZhenXinMa
 * @date 2020/2/13 21:51
 */
@Api(value = "页面预览接口")
public interface CmsPreviewControllerApi {

    /**
     *
     * @param pageId
     * @return
     */
    @ApiOperation(value = "根据页面ID进行预览")
    @ApiImplicitParam(name = "pageId",required = true,dataType = "String")
    String preview(String pageId);

}
