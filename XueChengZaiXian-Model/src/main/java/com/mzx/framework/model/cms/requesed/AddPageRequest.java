package com.mzx.framework.model.cms.requesed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 20:23
 */
@Data
@ToString
@Accessors(chain = true)
@ApiModel(value = "增加CMS页面模型")
public class AddPageRequest implements Serializable {

    @ApiModelProperty("页面ID")
    private String id;

    @ApiModelProperty("站点ID 必填")
    private String siteId;

    @ApiModelProperty("页面名称 必填")
    private String pageName;

    @ApiModelProperty("页面别名  选填")
    private String pageAliase;

    @ApiModelProperty("页面访问地址")
    private String pageWebPath;

    @ApiModelProperty("未知")
    private String pageParameter;

    @ApiModelProperty("物理路径")
    private String pagePhysicalPath;

    @ApiModelProperty("页面是静态")
    private String pageType;

    @ApiModelProperty("页面模板")
    private String pageTemplate;

    @ApiModelProperty("页面静态化内容")
    private String pageHtml;

    @ApiModelProperty("页面状态")
    private String pageStatus;


    @ApiModelProperty("页面创建时间")
    private String pageCreateTime;

    @ApiModelProperty("模板ID")
    private String templateId;

    @ApiModelProperty("模板文件ID")
    private String templateFileId;

    @ApiModelProperty("静态文件ID")
    private String htmlFileId;

    @ApiModelProperty("数据URL")
    private String dataUrl;

}
