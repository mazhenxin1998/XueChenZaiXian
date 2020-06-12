package com.mzx.media.service;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.media.response.CheckChunkResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 具体完成分块文件上传.
 * 文件注册: 前台页面在上传文件的时候会调用上传文件前的钩子方法进行文件注册. 所谓的文件注册就是检查当前文件系统中是否有该文件或者MongoDB
 * 服务中是否有该文件的信息.如果有责返回false不用上传.
 * 文件注册完毕之后,前台会触发文件分块检测方法：具体检测也就是看看该文件是否存在.我这里的实现步骤是看存放分块文件的文件夹是否存在存在就返回false
 * 不存在就返回true;
 * 文件上传: 当分块检测方法执行完毕之后,会根据返回的结果值来确定是否上传分块文件. 具体就是根据MultipartFile 获取到上传文件的流,在通过IOUtils
 * copy到指定的文件. 完成快上传.
 * 文件合并: 当文件块全部上传之后将会对所有的分块文件进行文件合并. 并输出到chunks目录之外.
 * 例如：D:\Server\FFmpeg\test\0\b\0bdc92b36e1ad0ffd9ea0b4bca1fca6b\chunks\{该文件夹下是所有的分块文件.}
 * 上传视屏的源文件:  D:\Server\FFmpeg\test\0\b\0bdc92b36e1ad0ffd9ea0b4bca1fca6b\md5.fileExt
 *
 * @author ZhenXinMa
 * @date 2020/5/17 18:07
 */
public interface IMediaUploadService {

    /**
     * 检查当前系统时用户是否已经上传过该文件.
     * 一级目录是MD5的第一个值.
     * 二级目录是MD5的第二个值.
     * 三级目录是MD5的第三个值.
     * 文件名是MD5+扩展名.
     *
     * @param fileMd5  上传的视屏文件的MD5值.
     * @param fileName 上传的视屏文件的名字.
     * @param fileSize 上传视屏的大小 以B为单位.
     * @param mimetype 上传的类型.
     * @param fileExt  上传文件的后缀.
     * @return
     */
    ResponseResult register(String fileMd5,
                            String fileName,
                            Long fileSize,
                            String mimetype,
                            String fileExt);

    /**
     * 检查上传文件的分块是否存在.
     * 主要的作用就是看看本地上是否有存储该分块的位置.
     *
     * @param fileMd5   上传文件的MDF值.
     * @param chunk     分块下标.
     * @param chunkSize 分块大小.
     * @return
     */
    CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize);

    /**
     * 合并上传过来的分块文件.
     * 并将其上传到MongoDB中.
     * 校验MD5值.
     *
     * @param fileMd5  上传视屏文件的MD5值.
     * @param fileName 上传视屏文件的名字.
     * @param fileSize 上传文件的大小.
     * @param mimetype 上传视屏的类型.
     * @param fileExt  上传视屏文件的后缀.
     * @return
     */
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
    ResponseResult uploadChunk(MultipartFile file, String fileMd5, Integer chunk);

}
