package com.mzx.api.meida;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.framework.model.media.response.CheckChunkResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZhenXinMa
 * @date 2020/5/16 22:11
 */
@Api(value = "媒资管理接口")
public interface MediaUploadControllerApi {

    /**
     * 检查当前系统时用户是否已经上传过该文件.
     *
     * @param fileMd5  上传的视屏文件的MD5值.
     * @param fileName 上传的视屏文件的名字.
     * @param fileSize 上传视屏的大小 以B为单位.
     * @param mimetype 上传的类型.
     * @param fileExt  上传文件的后缀.
     * @return
     */
    @ApiOperation(value = "上传文件注册(校验上传的文件是否存在.)")
    ResponseResult register(String fileMd5,
                            String fileName,
                            Long fileSize,
                            String mimetype,
                            String fileExt);

    /**
     * 检查上传文件的分块是否存在.
     *
     * @param fileMd5   上传文件的MDF值.
     * @param chunk     分块下标.
     * @param chunkSize 分块大小.
     * @return
     */
    @ApiOperation(value = "校验要上传的分块是否存在.")
    CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize);

    /**
     * 合并上传过来的分块文件.
     *
     * @param fileMd5  上传视屏文件的MD5值.
     * @param fileName 上传视屏文件的名字.
     * @param fileSize 上传文件的大小.
     * @param mimetype 上传视屏的类型.
     * @param fileExt  上传视屏文件的后缀.
     * @return
     */
    @ApiOperation(value = "将上传进来的文件进行合并")
    ResponseResult mergeChunks(String fileMd5,
                               String fileName,
                               Long fileSize,
                               String mimetype,
                               String fileExt);

    /**
     * 上传分快.
     *
     * @param file    要上传的文件.
     * @param fileMd5 要上传的文件的MD5值.
     * @param chunk   要上传的文件的下标.
     * @return
     */
    @ApiOperation(value = "接受上传进来的分块文件.")
    ResponseResult uploadChunk(MultipartFile file, String fileMd5, Integer chunk);


}
