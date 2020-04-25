package com.mzx.framework.model.cms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 18:26
 *
 *      网站子站点的实体类
 *
 */
@Data
@ToString
@Document(collection = "cms_site")
@ApiModel(value = "查询条件模型")
public class CmsSite {

    @Id
    @ApiModelProperty(value = "站点ID")
    private String siteId;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty( value = "站点域名" )
    private String siteDomain;

    @ApiModelProperty( value = "站点端口")
    private String sitePort;

    @ApiModelProperty( value = "站点访问地址")
    private String siteWebPath;

    @ApiModelProperty( value = "创建时间")
    private Date siteCreateTime;

    @ApiModelProperty( value = "站点物理路径")
    private String sitePhysicalPath;

}
