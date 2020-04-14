package com.mzx.server.managecms.service;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/2/15 13:13
 */
public interface IPagePreviewService {

    String preview(String pageID);

    /**
     * 将页面文件进行静态化
     *
     * @param pageID
     * @return
     */
    String generateHtml(String pageID);

    /**
     * 根据页面ID获取到该页面所需要的模型数据Model.
     *
     * @param pageID 当前页面ID.
     * @return pageID需要的模型数据Model.
     */
    Map getMapBody(String pageID);

    /**
     * 根据页面ID获取到该页面的字符串形式.
     *
     * 连接MongoDB进行页面模板查询.
     * @param pageID 要返回的页面模板的字符串形式.
     * @return 页面模板的字符串.
     */
    String content(String pageID);

    /**
     * 通过页面ID获取到该页面所对应的模板ID.
     * @param pageID 页面ID.
     * @return 页面ID对应的模板文件ID.
     */
    String getFileID(String pageID);

}
