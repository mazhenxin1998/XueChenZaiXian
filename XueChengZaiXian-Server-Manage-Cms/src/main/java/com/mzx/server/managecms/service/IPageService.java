package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.framework.model.course.response.CmsPostPageResult;


/**
 * @author ZhenXinMa
 * @date 2020/2/6 13:10
 */
public interface IPageService {

    /**
     * 注意异常：
     * 1. 参数异常
     * 2. 查询结果异常
     *
     * @param page             第几页
     * @param size             每个页面上的数量
     * @param queryPageRequest 查询条件的接口
     * @return 查询结果
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 注意异常：
     * 1. 参数异常
     * 2. 查询结果异常
     *
     * @param name
     * @return
     */
    CmsPage findByPageName(String name);

    /**
     * 注意异常：
     * 1. 参数异常
     * 2. 返回结果异常
     * <p>
     * 根据站点ID进行模糊查询
     *
     * @param queryPageRequest 查询条件模型
     * @return
     */
    QueryResponseResult get(QueryPageRequest queryPageRequest);

    /**
     * 注意异常：
     * 1. 参数异常
     * 2. 要增加的页面已经存在了
     * <p>
     * 增加新的CMS页面
     * 增加之前需要判断该页面是否存在
     *
     * @param request
     * @return
     * @since 1.8
     */
    QueryResponseResult add(CmsPage request);

    /**
     * 注意异常：
     * 1. 参数异常
     * 2. 数据库中没有该ID对应的page但是仍然删除异常
     *
     * @param pageID
     * @return
     */
    QueryResponseResult delete(String pageID);

    /**
     * 异常：
     * 1. 参数
     * 2. 更新不存在的page
     *
     * @param request
     * @return
     */
    QueryResponseResult update(CmsPage request, String pageID);

    String getPageHtml(String pageID);

    CmsPage getByID(String ID);

    /**
     * 根据ID发布页面到GridFS上  发布页面.
     *
     * @param pageID
     * @return
     */
    ResponseResult publishPage(String pageID);

    /**
     * 概要描述：根据页面ID和Content{@code String}类型页面模型+数据.
     *
     * <p>
     * 详细描述：将htmlContent保存到GridFS中，并且将保存的FileID更新到当前的cmsPage中.
     * 注意：保存之前要查看GridFS中是否存在该文件 如果存在则删除,然后在进行保存.
     * </p>
     *
     * @param pageID
     * @param htmlContent
     * @return
     */
    CmsPage saveHtml(String pageID, String htmlContent);

    /**
     * 查询.
     *
     * @param pageName
     * @param siteID
     * @param webPath
     * @return
     */
    CmsPage getByPageNameAndSiteIdAndPageWebPath(String pageName, String siteID, String webPath);

    /**
     * 增加CmsPage并且返回.
     * <p>
     * 调用该方法时，传进来的参数必须是一个完整的页面CmsPage因为要做递归调用,所以在方法体内不需要对参数进行校验.
     *
     * @param page
     * @return
     */
    CmsPage addPage(CmsPage page);

    /**
     * 页面一键发布的具体实现.
     *
     * 将参数传进来的Page进行发布.
     * @param cmsPage
     * @return
     */
    CmsPostPageResult postPageQuick(CmsPage cmsPage);

    /**
     * 将参数中的字符串,拼接成一个字符串.
     *
     * 需要注意的是要对参数进行为空判断.
     * @param args
     * @return
     */
    String appendString(String... args);


}
