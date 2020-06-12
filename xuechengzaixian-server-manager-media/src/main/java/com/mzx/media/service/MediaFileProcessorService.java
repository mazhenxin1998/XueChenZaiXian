package com.mzx.media.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.media.request.QueryMediaFileRequest;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 15:12
 */
public interface MediaFileProcessorService {

    /**
     * 媒资分页查询.
     *
     * @param page
     * @param size
     * @param request
     * @return
     */
    QueryResponseResult listMediaFile(String page, String size, QueryMediaFileRequest request);

    /**
     * 媒资管理列表 开始处理接口.
     *
     * @param mediaFileID
     * @return
     */
    ResponseResult mediaFileProcess(String mediaFileID);
}
