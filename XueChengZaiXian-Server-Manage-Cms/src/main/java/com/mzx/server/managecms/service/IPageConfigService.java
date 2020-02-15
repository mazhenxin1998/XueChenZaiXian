package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsConfig;



/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:37
 */
public interface IPageConfigService {

    /**
     * @param id  根据id查询CmsConfig
     * @return
     */
    CmsConfig get(String id);

    /**
     *  查询所有CmsConfig
     * @return  返回所有CmsConfig
     */
    QueryResponseResult get();

    ResponseResult add(CmsConfig cmsConfig);

    ResponseResult delete(String id);

    ResponseResult update(String id,CmsConfig cmsConfig);

    QueryResponseResult getValue();


}
