package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsFileControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.server.managecms.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 22:00
 */
@RestController
@RequestMapping(value = "/cms/file")
public class CmsFileController implements CmsFileControllerApi {

    @Autowired
    private IFileService service;

    @Override
    @PostMapping(value = "/add")
    public ResponseResult add(MultipartFile file, HttpServletRequest request) {
        return service.add(file,request);
    }

    @Override
    @GetMapping(value = "/delete/{id}")
    public ResponseResult delete(@PathVariable(value = "id") String id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public QueryResponseResult get(@PathVariable(value = "id") String id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/getName/{name}")
    public QueryResponseResult getByName(@PathVariable(value = "name") String name) {
        return service.getName(name);
    }

    @Override
    @GetMapping(value = "/get")
    public QueryResponseResult get() {
        return service.get();
    }
}
