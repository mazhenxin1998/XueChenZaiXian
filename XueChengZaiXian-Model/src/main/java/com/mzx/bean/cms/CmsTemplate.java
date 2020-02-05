package com.mzx.bean.cms;

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
public class CmsTemplate {


    //站点ID
    private String siteId;
    //模版ID
    @Id
    private String templateId;
    //模版名称
    private String templateName;
    //模版参数
    private String templateParameter;

    //模版文件Id
    private String templateFileId;

}
