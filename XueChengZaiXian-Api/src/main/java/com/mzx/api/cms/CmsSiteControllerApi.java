package com.mzx.api.cms;

import com.mzx.common.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 16:07
 */
@Api(value = "站点查询接口" ,description = "对站点进行查询")
public interface CmsSiteControllerApi {

    @ApiOperation(value = "查询所有站点")
    public QueryResponseResult get();


}
