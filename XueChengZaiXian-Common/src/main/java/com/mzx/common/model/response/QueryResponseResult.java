package com.mzx.common.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:58
 */
@Data
@ToString
@NoArgsConstructor
public class QueryResponseResult extends ResponseResult implements Serializable {

    QueryResult queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult queryResult){
        super(resultCode);
        this.queryResult = queryResult;
    }

}
