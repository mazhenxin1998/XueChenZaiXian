package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    /**
     *  需要先根据ID看看该Template是否存在
     * @param cmsTemplate
     * @return
     */
    ResponseResult add(CmsTemplate cmsTemplate);

    ResponseResult delete(String id);

    ResponseResult update(String id,CmsTemplate cmsTemplate);

    ResponseResult upload(MultipartFile file, HttpServletRequest request);

}
