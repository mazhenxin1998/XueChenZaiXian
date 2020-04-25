package com.mzx.server.cmsclient.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.CmsSite;
import com.mzx.framework.model.cms.response.CmsCode;
import com.mzx.server.cmsclient.dao.CmsPageRepository;
import com.mzx.server.cmsclient.dao.CmsSiteRepository;
import com.mzx.server.cmsclient.service.IPageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Optional;
import java.util.Queue;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 15:17
 */
@Service
public class PageServiceImpl implements IPageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Autowired
    private CmsPageRepository pageRepository;

    @Autowired
    private CmsSiteRepository siteRepository;

    @Override
    public void savePageToServerPath(String pageID) {

        if (StringUtils.isEmpty(pageID)) {

            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        Optional<CmsPage> optional = pageRepository.findById(pageID);
        String htmlFileID = "";
        String siteID = "";
        if (!optional.isPresent()) {

            /*报错.*/
            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }

        CmsPage cmsPage = optional.get();
        htmlFileID = cmsPage.getHtmlFileId();
        siteID = cmsPage.getSiteId();
        CmsSite cmsSite = this.findSiteBySiteID(siteID);
        /*页面保存地址为站点的物理地址+页面的访问路径(教程上是页面的物理地址.)*/
        String path = cmsSite.getSitePhysicalPath() + cmsPage.getPageWebPath()+cmsPage.getPageName();
        // 查询页面静态文件
        InputStream inputStream = this.findFileByFileID(htmlFileID);
        if (inputStream == null) {

            ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
        }

        File file = new File(path);
        if (!file.exists()) {

            try {

                file.createNewFile();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

        try {

            //   保存
            FileOutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public CmsSite findSiteBySiteID(String siteID) {
        if (StringUtils.isEmpty(siteID)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        Optional<CmsSite> optional = siteRepository.findById(siteID);
        if (!optional.isPresent()) {
            ThrowException.exception(CmsCode.CMS_SITE_NOT_FIND);
        }
        return optional.get();
    }

    public InputStream findFileByFileID(String fileID) {

        if (StringUtils.isEmpty(fileID)) {

            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileID)));
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file, downloadStream);
        try {

            return resource.getInputStream();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

}
