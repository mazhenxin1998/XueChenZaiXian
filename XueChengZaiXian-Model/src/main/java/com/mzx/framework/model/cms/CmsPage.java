package com.mzx.framework.model.cms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.chart.ValueAxis;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 17:42
 */
@Data
@ToString
@Accessors(chain = true)
@Document(collection = "cms_page")
@ApiModel
public class CmsPage implements Serializable {

    @ApiModelProperty(value = "页面ID")
    private String id;

    @ApiModelProperty(value = "站点ID      必填")
    private String siteId;

    @ApiModelProperty(value = "页面名称     必填")
    private String pageName;

    @ApiModelProperty(value = "页面别名     选填")
    private String pageAliase;

    @ApiModelProperty(value = "页面访问地址  必填")
    private String pageWebPath;

    @ApiModelProperty(value = "未知          可填")
    private String pageParameter;

    @ApiModelProperty(value = "物理路径       必填")
    private String pagePhysicalPath;

    @ApiModelProperty(value = "页面类型        必填")
    private String pageType;

    @ApiModelProperty(value = "页面模板        选填")
    private String pageTemplate;

    @ApiModelProperty(value = "页面静态化       必填")
    private String pageHtml;

    @ApiModelProperty(value = "页面状态          选填")
    private String pageStatus;

    @ApiModelProperty(value = "页面创建时间       必填")
    private String pageCreateTime;

    @ApiModelProperty(value = "模板ID             必填")
    private String templateId;

    //模板文件ID
//    private String templateFileId;

    @ApiModelProperty(value = "静态文件ID          选填")
    private String htmlFileId;

    @ApiModelProperty(value = "数据URL           不必填")
    private String dataUrl;

    //参数列表
//    private List<CmsPageParam> pageParams;


}
