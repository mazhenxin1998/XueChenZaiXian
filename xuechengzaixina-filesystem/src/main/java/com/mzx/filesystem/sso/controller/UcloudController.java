package com.mzx.filesystem.sso.controller;


import com.mzx.api.filesystem.IUcloudControllerApi;
import com.mzx.common.model.response.ResponseData;
import com.mzx.filesystem.sso.service.IUcloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;


/**
 * @author lenovo
 */
@RestController
@RequestMapping(value = "/filesystem")
@Slf4j
public class UcloudController implements IUcloudControllerApi {

    @Resource
    private IUcloudService ucloudService;

    @GetMapping(value = "/t")
    public String t2(){
        return "SUCCESS";
    }

    @Override
    @PostMapping(value = "/addCoursePhoto")
    public ResponseData addCoursePhoto(MultipartFile file,HttpServletRequest request) {
        log.info("----------------上传图片方法成功了");
        String url = "";
        ResponseData responseData = null;
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            responseData = ucloudService.addCoursePhoto(inputStream, contentType, originalFilename);
            /*返回图片的URL*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------------------URL:  "+responseData);

        return responseData;
    }









}
