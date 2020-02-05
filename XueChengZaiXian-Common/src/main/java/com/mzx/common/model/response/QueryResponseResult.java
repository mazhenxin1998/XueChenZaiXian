package com.mzx.common.model.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:58
 */
@Data
@ToString
public class QueryResponseResult extends ResponseResult {

    QueryResult queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult queryResult){
        super(resultCode);
        this.queryResult = queryResult;
    }

}
