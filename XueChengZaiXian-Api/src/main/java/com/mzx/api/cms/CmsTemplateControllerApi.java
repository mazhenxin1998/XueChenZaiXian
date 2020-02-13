package com.mzx.api.cms;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @ApiOperation(value = "根据ID删除Template")
    @ApiImplicitParam(name = "id", value = "查找ID",required = true,dataType = "String",paramType = "path")
    ResponseResult delete(String id);

    @ApiOperation(value = "增加Template")
    ResponseResult add(CmsTemplate template);

    @ApiOperation(value = "根据ID修改Template")
    @ApiImplicitParam(name = "id",value = "修改ID",required = true,dataType = "String")
    ResponseResult update(String id,CmsTemplate cmsTemplate);

    @ApiOperation(value = "上传文件")
    ResponseResult upload(MultipartFile file, HttpServletRequest request);

}
