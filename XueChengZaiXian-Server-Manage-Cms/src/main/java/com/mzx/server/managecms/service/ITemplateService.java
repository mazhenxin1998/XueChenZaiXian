package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:10
 */
public interface ITemplateService {

    /**
     *  查询所有页面模板
     * @return  返回所以页面模板
     */
    QueryResponseResult get();

}
