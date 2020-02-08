package com.mzx.framework.model.cms.requesed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @author ZhenXinMa
 * @date 2020/2/4 20:52
 */
@Data
@ToString
@ApiModel(value = "查询条件模型" ,description = "只能填入该参数列表中的一项")
public class QueryPageRequest {

    //接收页面查询的查询条件
    //站点id
    //站点id
    @ApiModelProperty(value = "站点ID")
    private String siteId;

    //页面ID
    @ApiModelProperty(value = "页面ID")
    private String pageId;

    //页面名称
    @ApiModelProperty(value = "页面名称")
    private String pageName;

    //别名
    @ApiModelProperty(value = "页面别名")
    private String pageAliase;

    //模版id
    @ApiModelProperty(value = "模板ID")
    private String templateId;

}
