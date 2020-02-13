package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsConfigControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsConfig;
import com.mzx.server.managecms.service.IPageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:36
 */
@RestController
@RequestMapping(value = "/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    private IPageConfigService service;

    @Override
    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public CmsConfig get(@PathVariable(value = "id") String id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/get")
    public QueryResponseResult get() {
        return service.get();
    }

    @Override
    @GetMapping(value = "/value/get")
    public QueryResponseResult getModelValues() {
        return service.getValue();
    }

    @Override
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody CmsConfig cmsConfig) {
        return service.add(cmsConfig);
    }

    @Override
    @GetMapping(value = "/delete/{id}")
    public ResponseResult delete(@PathVariable(value = "id") String id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/update/{id}")
    public ResponseResult update(@PathVariable(value = "id") String id,CmsConfig cmsConfig) {
        return service.update(id,cmsConfig);
    }
}
