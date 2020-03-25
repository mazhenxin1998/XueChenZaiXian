package com.mzx.framework.model.cms.response;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import com.mzx.framework.model.cms.CmsPage;
import lombok.Data;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:50
 */
@Data
public class CmsPageResult extends ResponseResult {
    CmsPage cmsPage;
    public CmsPageResult(ResultCode resultCode, CmsPage cmsPage) {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}