package com.mzx.framework.model.cms.response;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:50
 */
@Data
@ToString
@NoArgsConstructor
public class CoursePreviewResult extends ResponseResult {
    public CoursePreviewResult(ResultCode resultCode, String url) {
        super(resultCode);
        this.url = url;
    }

    String url;
}
