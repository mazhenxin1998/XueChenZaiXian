package com.mzx.framework.model.cms.response;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import lombok.Data;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:51
 */
@Data
public class GenerateHtmlResult extends ResponseResult {
    String html;
    public GenerateHtmlResult(ResultCode resultCode, String html) {
        super(resultCode);
        this.html = html;
    }
}

