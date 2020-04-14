package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsPreviewControllerApi;
import com.mzx.server.managecms.service.IPagePreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
    @GetMapping(value = "/page/preview/{id}")
    public String preview(@PathVariable(value = "id") String id,
                          HttpServletResponse response) {

        response.setHeader("Content‐type","text/html;charset=utf‐8");
        return service.preview(id);
    }

    @GetMapping(value = "/")
    public String p(){
        /*测试.*/
        return service.preview("5a795ac7dd573c04508f3a56");
    }

    @Override
    @GetMapping(value = "/get/model/{id}")
    public Map getMap(@PathVariable(value = "id") String id){

        return service.getMapBody(id);
    }

    @Override
    @GetMapping(value = "/get/fileID/{id}")
    public String getFileID(@PathVariable(value = "id") String id){

        return service.getFileID(id);
    }

    @Override
    @GetMapping(value = "/get/content/{id}")
    public String getContent(@PathVariable(value = "id") String id){

        return service.content(id);
    }

}
