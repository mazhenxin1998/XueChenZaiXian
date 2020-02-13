package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsTemplateControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsTemplate;
import com.mzx.server.managecms.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Override
    @GetMapping(value = "/delete/{id}")
    public ResponseResult delete(@PathVariable(value = "id") String id) {
        return service.delete(id);
    }

    @Override
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody CmsTemplate template) {
        return service.add(template);
    }

    @Override
    @PostMapping(value = "/update/{id}")
    public ResponseResult update(@PathVariable(value = "id") String id, @RequestBody CmsTemplate cmsTemplate) {
        return service.update(id,cmsTemplate);
    }

    @Override
    @PostMapping(value = "/upload")
    public ResponseResult upload(MultipartFile file, HttpServletRequest request) {
        System.out.println("upload  controller执行了");
        return service.upload(file,request);
    }
}
