package com.mzx.bean.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 18:21
 */
@Data
@ToString
@Document(collection = "cms_config")
public class CmsConfig {

    // 配置文件的ID
    @Id
    private String id;

    //配置文件的名字
    private String name;

    // 配置文件包含的model
    private List<CmsConfigModel> model;

}
