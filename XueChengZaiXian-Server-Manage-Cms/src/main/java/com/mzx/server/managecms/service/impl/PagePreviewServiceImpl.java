package com.mzx.server.managecms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.CmsTemplate;
import com.mzx.framework.model.cms.response.CmsCode;
import com.mzx.server.managecms.dao.CmsTemplateRepository;
import com.mzx.server.managecms.service.IPagePreviewService;
import com.mzx.server.managecms.service.IPageService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/15 13:14
 */
@Service
public class PagePreviewServiceImpl implements IPagePreviewService {

    @Autowired
    private IPageService pageService;

    @Autowired
    private RestTemplate template;

    @Autowired
    private CmsTemplateRepository templateRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;


    @Override
    public String preview(String pageID) {

        String content = "";
        if (StringUtils.isEmpty(pageID)) {

            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        } else {

            String s = this.generateHtml(pageID);
            if (StringUtils.isEmpty(s)) {

                ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
            } else {

                content = s;
            }

        }

        return content;
    }

    @Override
    public synchronized String generateHtml(String pageID) {

        //   获取数据模型
        Map map = this.getMapBody(pageID);
        if (map == null) {

            //ThrowException.exception(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }

        // 获取模板字符串 从GridFS中获取模板文件 并转换为字符串
        String content = this.content(pageID);
        if (StringUtils.isEmpty(content)) {

            ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        // 静态化
        Configuration configuration = new Configuration(Configuration.getVersion());
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.putTemplate("template", content);
        configuration.setTemplateLoader(loader);

        try {

            Template template = configuration.getTemplate("template");
            String fileContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            if (StringUtils.isEmpty(fileContent)) {

                ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
            } else {

                return fileContent;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String content(String pageID) {

        String fileID = this.getFileID(pageID);
        if (StringUtils.isEmpty(fileID)) {

            ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
        }

        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileID)));
        if (file == null) {

            ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
        }

        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file, downloadStream);
        // 将流转换为字符串
        try {

            String content = IOUtils.toString(resource.getInputStream(), "utf-8");
            if (StringUtils.isEmpty(content)) {

                ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
            } else {

                return content;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getFileID(String pageID) {

        CmsPage cmsPage = pageService.getByID(pageID);
        if (cmsPage == null) {

            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }

        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId)) {

            ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
        }

        Optional<CmsTemplate> o = templateRepository.findById(templateId);
        String fileID = null;
        if (o.isPresent()) {

            CmsTemplate template = o.get();
            fileID = template.getTemplateFileId();
            if (StringUtils.isEmpty(fileID)) {

                ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
            } else {

                return fileID;
            }

        }

        return null;
    }

    @Override
    public Map getMapBody(String pageID) {

        CmsPage cmsPage = pageService.getByID(pageID);
        if ( cmsPage == null ) {

            return null;
        }else{

            /*可能为空 dataUrl*/
            String dataUrl = cmsPage.getDataUrl();
            if (StringUtils.isEmpty(dataUrl)) {

                ThrowException.exception(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
            }

            ResponseEntity<Map> entity = template.getForEntity(dataUrl, Map.class);
            /* entity的数据结构.teachplanNode.children */
            Map body = entity.getBody();
            return body;
        }

    }

}
