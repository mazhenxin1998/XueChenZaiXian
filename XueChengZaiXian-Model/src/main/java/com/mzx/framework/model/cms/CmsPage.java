package com.mzx.framework.model.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 17:42
 */
@Data
@ToString
@Document(collection = "cms_page")
public class CmsPage {

    // 页面ID  主键
    private String id;

    // 站点ID  2
    private String siteId;

    // 页面名称  2
    private String pageName;

    // 页面别名  2
    private String pageAliase;

    // 页面访问地址 相对于Nginx服务器 2
    private String pageWebPath;

    private String pageParameter;

    // 物理路径 2
    private String pagePhysicalPath;

    // 页面是静态、动态 2
    private String pageType;

    // 页面模板
    private String pageTemplate;

    //页面静态化内容
    private String pageHtml;

    // 页面状态
    private String pageStatus;

    // 页面创建时间 2
    private String pageCreateTime;

    // 模板ID  2
    private String templateId;

    //模板文件ID
//    private String templateFileId;

    // 静态文件ID 2
    private String htmlFileId;

    // 数据URL
    private String dataUrl;

    //参数列表
//    private List<CmsPageParam> pageParams;


}
