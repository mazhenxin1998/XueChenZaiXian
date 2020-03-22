package com.mzx.server.managecms.service;

/**
 * @author ZhenXinMa
 * @date 2020/2/15 13:13
 */
public interface IPagePreviewService {

    String preview(String pageID);

    /**
     *  将页面文件进行静态化
     * @param pageID
     * @return
     */
    String generateHtml(String pageID);

}
