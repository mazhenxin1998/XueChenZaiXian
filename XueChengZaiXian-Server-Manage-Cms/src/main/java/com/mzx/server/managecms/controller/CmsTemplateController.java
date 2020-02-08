package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsTemplateControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.server.managecms.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:09
 */
@RestController
@RequestMapping(value = "/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Autowired
    private ITemplateService service;

    @Override
    @GetMapping(value = "/get")
    public QueryResponseResult get() {
        return service.get();
    }
}
