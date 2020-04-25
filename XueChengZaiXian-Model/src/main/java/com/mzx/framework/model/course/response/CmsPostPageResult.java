package com.mzx.framework.model.course.response;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 页面发布接口.
 *
 * @author ZhenXinMa
 * @date 2020/4/14 22:14
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CmsPostPageResult extends ResponseResult implements Serializable {

    String pageUrl;

    public CmsPostPageResult(ResultCode resultCode, String pageUrl) {

        super(resultCode);
        this.pageUrl = pageUrl;
    }

}
