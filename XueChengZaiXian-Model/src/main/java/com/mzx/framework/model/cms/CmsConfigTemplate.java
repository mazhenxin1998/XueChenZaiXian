package com.mzx.framework.model.cms;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 22:31
 *
 *  "isshow" : "1",
 *                   "children" : null,
 *                   "name" : "jQuery",
 *                   "orderby" : "1-1-3",
 *                   "id" : "1-1-3",
 *                   "label" : "jQuery",
 *                   "isleaf" : "1",
 *                   "parentid" : null
 *                 }],
 *
 */
@Data
@ToString
@Accessors(chain = true)
public class CmsConfigTemplate {

    private String id;
    private String name;
    private String model_key;
    private String model_name;
    private String model_url;
    private Map model_mapValue;
    private List<String> model_value;
    private String isshow;
    private String childrenName;
    private String orderby;
    private String childrenId;
    private String label;
    private String isleaf;
    private String parentid;



}
