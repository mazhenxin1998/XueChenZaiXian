package com.mzx.common.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 19:53
 */
@Data
@ToString
public class CmsConfigQueryResult {

    List<Object> list;

    List<Object> model;

    private Long listTotal;

    private Long modelTotal;

}
