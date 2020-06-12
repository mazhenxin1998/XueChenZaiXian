package com.mzx.media.controller;

import com.mzx.api.meida.MediaFileProcessorControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.media.request.QueryMediaFileRequest;
import com.mzx.media.service.MediaFileProcessorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 15:11
 */
@RestController
@RequestMapping(value = "/media/file")
public class MediaFileProcessorController implements MediaFileProcessorControllerApi {

    @Resource
    private MediaFileProcessorService mediaFileProcessorService;


    @Override
    @GetMapping(value = "/list/{page}/{size}")
    public QueryResponseResult listMediaFile(@PathVariable(value = "page") String page,
                                             @PathVariable(value = "size") String size,
                                             QueryMediaFileRequest request) {

        System.out.println(page);
        System.out.println(size);
        return mediaFileProcessorService.listMediaFile(page, size, request);
    }

    @Override
    @GetMapping(value = "/process/{mediaFileID}")
    public ResponseResult mediaFileProcess(@PathVariable String mediaFileID) {

        return mediaFileProcessorService.mediaFileProcess(mediaFileID);
    }
}
