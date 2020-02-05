package com.mzx.framework.model.cms.requesed;

import lombok.Data;
import lombok.ToString;


/**
 * @author ZhenXinMa
 * @date 2020/2/4 20:52
 */
@Data
@ToString
public class QueryPageRequest {

    //接收页面查询的查询条件
    //站点id
    //站点id
    private String siteId;
    //页面ID
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模版id
    private String templateId;

}