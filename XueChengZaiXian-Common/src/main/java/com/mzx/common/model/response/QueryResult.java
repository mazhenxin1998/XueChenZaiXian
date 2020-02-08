package com.mzx.common.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:58
 */
@Data
@ToString
@NoArgsConstructor
public class QueryResult<T> {

    /**
     *  数据列表
     */
    private List<T> list;

    /**
     *  数据总数
     */
    private Long total;


}
