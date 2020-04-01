package com.mzx.servermanager.service;

import com.mzx.common.model.response.QueryResponseResult;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 20:10
 */
public interface ISysDictionaryService {

    QueryResponseResult getByDName(String DName);

    QueryResponseResult getByType(String type);



}
