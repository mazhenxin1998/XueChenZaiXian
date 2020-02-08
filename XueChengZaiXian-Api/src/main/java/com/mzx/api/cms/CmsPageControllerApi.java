package com.mzx.api.cms;


import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.AddPageRequest;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import io.swagger.annotations.*;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 15:30
 */
@Api(value = "CMS页面查询接口",description = "实现增删改查")
public interface CmsPageControllerApi {

    /**
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation(value = "分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "查询页码数",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "查询页显示页面的数量",required = true,paramType = "path",dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     *  根据条件进行查询
     * @param queryPageRequest   查询条件
     * @return
     */
    @ApiOperation(value = "根据条件进行查询")
    public QueryResponseResult get(QueryPageRequest queryPageRequest);

    @ApiOperation(value = "增加CMS页面")
    public QueryResponseResult add(CmsPage request);

    @ApiOperation(value = "删除CMS页面")
    public QueryResponseResult delete(QueryPageRequest request);

    @ApiOperation(value = "修改CMS页面")
    public QueryResponseResult update(CmsPage request);

}
