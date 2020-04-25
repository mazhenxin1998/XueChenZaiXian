package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.cms.CmsSite;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 15:57
 */
public interface ISiteService {

    QueryResponseResult get();

    /**
     * 通过站点ID从MongoDB中获取到该站点的信息.
     *
     * @param siteID
     * @return
     */
    CmsSite getByID(String siteID);

}
