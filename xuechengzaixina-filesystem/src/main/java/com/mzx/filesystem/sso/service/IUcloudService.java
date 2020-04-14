package com.mzx.filesystem.sso.service;



import com.mzx.common.model.response.ResponseData;

import java.io.InputStream;

/**
 * @author ZhenXinMa
 * @date 2020/4/6 11:04
 */
public interface IUcloudService {

    /**
     * 创建上传文件的方法
     * <p>
     * 返回上传文件的最终URL地址(猜想)
     * mimeType :  文件类型
     * <p>
     * Spring 上传文件的机制是通过流来实现的
     *
     *      fileInputStream流 执行要存入文件的流
     *
     *   这个是根据流来实现
     */
     ResponseData addCoursePhoto(InputStream fileInputStream, String mimeType, String fileName);



}
