package com.mzx.framework.model.cms;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 18:22
 */
@Data
@ToString
public class CmsConfigModel {

    private String key;
    private String name;
    private String url;
    private Map mapValue;
    private String value;

}
