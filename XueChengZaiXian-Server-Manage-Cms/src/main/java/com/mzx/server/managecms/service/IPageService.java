package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.AddPageRequest;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;


/**
 * @author ZhenXinMa
 * @date 2020/2/6 13:10
 */
public interface IPageService {

    /**
     *
     * @param page                第几页
     * @param size                每个页面上的数量
     * @param queryPageRequest    查询条件的接口
     * @return                    查询结果
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    CmsPage findByPageName(String name);

    /**
     *  根据站点ID进行模糊查询
     * @param queryPageRequest 查询条件模型
     * @return
     */
    QueryResponseResult get(QueryPageRequest queryPageRequest);

    /**
     *  增加新的CMS页面
     * @param request
     * @return
     */
    QueryResponseResult add(CmsPage request);

    /**
     *
     * @param pageID
     * @return
     */
    QueryResponseResult delete(String pageID);

    /**
     *
     * @param request
     * @return
     */
    QueryResponseResult update(CmsPage request,String pageID);


}
