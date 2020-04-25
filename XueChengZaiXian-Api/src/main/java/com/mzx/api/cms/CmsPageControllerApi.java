package com.mzx.api.cms;


import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.AddPageRequest;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.framework.model.course.response.CmsPostPageResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 15:30
 */
@Api(value = "CMS页面查询接口", description = "实现增删改查")
public interface CmsPageControllerApi {

    /**
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation(value = "分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "查询页码数", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "查询页显示页面的数量", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 根据条件进行查询
     *
     * @param queryPageRequest 查询条件
     * @return
     */
    @ApiOperation(value = "根据条件进行查询")
    public QueryResponseResult get(QueryPageRequest queryPageRequest);

    @ApiOperation(value = "增加CMS页面")
    public QueryResponseResult add(CmsPage request);

    @ApiOperation(value = "删除CMS页面")
    public QueryResponseResult delete(String pageID);

    @ApiOperation(value = "修改CMS页面")
    public QueryResponseResult update(CmsPage request, String pageID);

    @ApiOperation(value = "页面发布")
    public ResponseResult publishCmsPage(String pageID);

    @ApiOperation(value = "根据页面名称查找")
    CmsPage getByName(String name);

    @ApiOperation(value = "增加一个CmsPage并且返回")
    CmsPage addCmsPage(CmsPage page);

    /**
     * 一键发布页面的接口.
     *
     * 传进来的参数必须是一个完整的页面类型,不需要自己再去设置各个属性的CmsPage.
     * @param cmsPage
     * @return
     */
    @ApiOperation(value = "页面一键发布接口")
    CmsPostPageResult postPageQuick(CmsPage cmsPage);



}
