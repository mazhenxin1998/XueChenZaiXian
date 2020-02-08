package com.mzx.framework.model.cms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 20:50
 */
@Data
@ToString
@Document(collection = "cms_template")
@ApiModel(value = "页面模板模型")
public class CmsTemplate {

    @ApiModelProperty(value = "站点ID")
    private String siteId;

    @ApiModelProperty(value = "模板ID")
    @Id
    private String templateId;

    @ApiModelProperty(value = "模板名称")
    private String templateName;

    @ApiModelProperty(value = "模板参数")
    private String templateParameter;

    @ApiModelProperty(value = "模板文件ID")
    private String templateFileId;

}
