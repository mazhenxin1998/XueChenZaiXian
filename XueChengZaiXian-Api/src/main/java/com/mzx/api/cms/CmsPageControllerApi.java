package com.mzx.api.cms;

import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.common.model.response.QueryResponseResult;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 15:30
 */
public interface CmsPageControllerApi {

    public QueryResponseResult findList(int number, int size, QueryPageRequest queryPageRequest);

}
