package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsPreviewControllerApi;
import com.mzx.server.managecms.service.IPagePreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhenXinMa
 * @date 2020/2/13 21:53
 */
@RestController
@RequestMapping(value = "/cms")
public class CmsPreviewController implements CmsPreviewControllerApi {

    @Autowired
    private IPagePreviewService service;

    @Override
    @GetMapping(value = "/preview/{id}")
    public String preview(@PathVariable(value = "id") String id) {
        return service.preview(id);
    }

    @GetMapping(value = "/")
    public String p(){
        return service.preview("5a795ac7dd573c04508f3a56");
    }

}
