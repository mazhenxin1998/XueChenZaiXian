package com.mzx.api.cms;

import com.mzx.bean.cms.request.QueryPageRequest;
import com.mzx.common.model.response.QueryResponseResult;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 15:30
 */
public interface CmsPageControllerApi {

    QueryResponseResult findList(int number, int size, QueryPageRequest queryPageRequest);

}
