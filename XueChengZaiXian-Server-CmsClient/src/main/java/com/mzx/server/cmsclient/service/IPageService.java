package com.mzx.server.cmsclient.service;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 15:16
 */
public interface IPageService {

    /**
     * 将静态文件生成在本地服务器上
     * 页面保存在本地服务器的路径为： 站点物理路径+页面的访问路径
     * @param pageID
     */
    void savePageToServerPath(String pageID);

}
