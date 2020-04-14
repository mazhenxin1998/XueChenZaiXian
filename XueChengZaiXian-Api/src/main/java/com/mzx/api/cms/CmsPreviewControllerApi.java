package com.mzx.api.cms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.naming.Name;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/2/13 21:51
 */
@Api(value = "页面预览接口")
public interface CmsPreviewControllerApi {

    /**
     * 对页面进行预览.
     * <p>
     * 将页面的模型数据Model和页面的字符串进行绑定,并将页面字符串以文件的形式输送到当前页面应该在的位置上.
     *
     * @param pageId 要预览的页面ID.
     * @return 返回的是该页面的字符串.
     */
    @ApiOperation(value = "根据页面ID进行预览")
    @ApiImplicitParam(name = "pageId", required = true, dataType = "String")
    String preview(String pageId, HttpServletResponse response);

    /**
     * 根据页面ID获取到该页面的模型数据.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据页面ID获取到该页面的模型数据.")
    @ApiImplicitParam(name = "id", required = true, dataType = "String")
    public Map getMap(String id);

    /**
     * 根据页面ID获取到该页面对应的字符串形式.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据页面ID获取到该页面对应的字符串形式.")
    @ApiImplicitParam(name = "id", required = true, dataType = "String")
    String getContent(String id);

    /**
     * 根据页面ID获取到该页面所对应的页面模板ID 而页面的字符串就是从该页面的模板中获取.
     *
     * @param id 要进行查询的页面ID.
     * @return 通过页面ID, 获取到该页面在MongoDB所对应的页面模板文件ID.
     */
    @ApiOperation(value = "根据页面ID获取到该页面所对应的页面模板ID 而页面的字符串就是从该页面的模板中获取")
    @ApiImplicitParam(name = "id", required = true, dataType = "String")
    String getFileID(String id);

}
