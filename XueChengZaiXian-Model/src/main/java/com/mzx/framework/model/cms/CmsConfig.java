package com.mzx.framework.model.cms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 18:21
 */
@Data
@ToString
@Accessors(chain = true)
@Document(collection = "cms_config")
@ApiModel
public class CmsConfig {

    @Id
    @ApiModelProperty(value = "配置文件的ID")
    private String id;

    @ApiModelProperty(value = "配置文件的名字")
    private String name;

    @ApiModelProperty(value = "配置文件包含的model")
    private List<CmsConfigModel> model;

}
