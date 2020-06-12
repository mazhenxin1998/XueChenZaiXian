package com.mzx.media.controller;

import com.mzx.api.meida.MediaUploadControllerApi;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.framework.model.media.response.CheckChunkResult;
import com.mzx.media.service.IMediaUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author ZhenXinMa
 * @date 2020/5/17 13:22
 */
@RestController
@RequestMapping(value = "/media")
public class MediaUploadController implements MediaUploadControllerApi {

    @Autowired
    private IMediaUploadService iMediaUploadService;

    @Override
    @PostMapping(value = "/upload/register")
    public ResponseResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        System.out.println("注册方法执行了");

        return iMediaUploadService.register(fileMd5, fileName, fileSize, mimetype, fileExt);
    }

    @Override
    @PostMapping(value = "/upload/checkchunk")
    public CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize) {
        System.out.println("检查文件块是否存在执行了");
        return iMediaUploadService.checkChunk(fileMd5, chunk, chunkSize);
    }

    @Override
    @PostMapping(value = "/upload/uploadchunk")
    public ResponseResult uploadChunk(@RequestParam(value = "file") MultipartFile file,
                                      @RequestParam(value = "fileMd5") String fileMd5,
                                      @RequestParam(value = "chunk") Integer chunk) {
        System.out.println("上传方法执行了");
        return iMediaUploadService.uploadChunk(file, fileMd5, chunk);
    }


    @Override
    @PostMapping(value = "/upload/mergechunks")
    public ResponseResult mergeChunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

        System.out.println("文件合并方法执行了");
        return iMediaUploadService.mergeChunks(fileMd5, fileName, fileSize, mimetype, fileExt);
    }

    @PostMapping(value = "/test")
    public ResponseResult test(@RequestParam(value = "file") MultipartFile file,
                               @RequestParam(value = "fileMd5") String fileMd5,
                               @RequestParam(value = "chunk") Integer chunk){
        System.out.println("test方法执行了");
        return iMediaUploadService.uploadChunk(file, fileMd5, chunk);
    }



}
