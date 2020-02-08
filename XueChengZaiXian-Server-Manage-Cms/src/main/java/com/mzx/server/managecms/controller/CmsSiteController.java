package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsSiteControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.server.managecms.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 16:11
 */
@RestController
@RequestMapping(value = "/cms/site")
public class CmsSiteController implements CmsSiteControllerApi {

    @Autowired
    private ISiteService service;

    @Override
    @GetMapping(value = "/getSite")
    public QueryResponseResult get() {
        System.out.println("方法执行了");
        return service.get();
    }
}
