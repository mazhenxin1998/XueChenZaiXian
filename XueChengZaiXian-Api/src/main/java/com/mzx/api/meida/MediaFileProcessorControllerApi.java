package com.mzx.api.meida;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.media.request.QueryMediaFileRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 14:39
 */
@Api(value = "媒资处理接口")
public interface MediaFileProcessorControllerApi {

    /**
     * 分页查询我的媒资列表.
     *
     * @param page    查询的页数.逻辑上的页数.
     * @param size    每一页的大小.逻辑上的大小.
     * @param request 请求参数.
     * @return
     */
    @ApiOperation(value = "查询媒资管理列表")
    QueryResponseResult listMediaFile(String page, String size, QueryMediaFileRequest request);

    /**
     * 前台用户点击开始处理，将会调用该接口.
     * 前台开始处理是为那些在上传视屏时候失败做的.
     *
     * @param mediaFileID
     * @return
     */
    @ApiOperation(value = "媒资列表 开始处理接口.")
    ResponseResult mediaFileProcess(String mediaFileID);

}
