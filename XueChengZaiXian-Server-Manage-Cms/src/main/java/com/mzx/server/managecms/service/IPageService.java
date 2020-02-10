package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;


/**
 * @author ZhenXinMa
 * @date 2020/2/6 13:10
 */
public interface IPageService {

    /**
     *      注意异常：
     *          1. 参数异常
     *          2. 查询结果异常
     *
     * @param page                第几页
     * @param size                每个页面上的数量
     * @param queryPageRequest    查询条件的接口
     * @return                    查询结果
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     *      注意异常：
     *          1. 参数异常
     *          2. 查询结果异常
     * @param name
     * @return
     */
    CmsPage findByPageName(String name);

    /**
     *      注意异常：
     *          1. 参数异常
     *          2. 返回结果异常
     *
     *  根据站点ID进行模糊查询
     * @param queryPageRequest 查询条件模型
     * @return
     */
    QueryResponseResult get(QueryPageRequest queryPageRequest);

    /**
     *      注意异常：
     *          1. 参数异常
     *          2. 要增加的页面已经存在了
     *
     *  增加新的CMS页面
     *  增加之前需要判断该页面是否存在
     * @param request
     * @return
     */
    QueryResponseResult add(CmsPage request);

    /**
     *      注意异常：
     *          1. 参数异常
     *          2. 数据库中没有该ID对应的page但是仍然删除异常
     * @param pageID
     * @return
     */
    QueryResponseResult delete(String pageID);

    /**
     *      异常：
     *          1. 参数
     *          2. 更新不存在的page
     *
     * @param request
     * @return
     */
    QueryResponseResult update(CmsPage request,String pageID);


}
