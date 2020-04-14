package com.mzx.server.managecms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mzx.common.exception.CustomException;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.CmsTemplate;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.framework.model.cms.response.CmsCode;
import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.dao.CmsTemplateRepository;
import com.mzx.server.managecms.mqproducer.IMessageProduceSender;
import com.mzx.server.managecms.service.IPageService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import sun.security.krb5.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @author ZhenXinMa
 * @date 2020/2/6 13:11
 */
@Service
@Slf4j
public class PageServiceImpl implements IPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IMessageProduceSender messageSender;

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Override
    public QueryResponseResult get(QueryPageRequest request) {

        System.out.println(request);
        QueryResponseResult result = null;
        QueryResult<CmsPage> queryResult = null;

        // 根据站点ID精确查询
        if ((!StringUtils.isEmpty(request.getSiteId())) && (StringUtils.isEmpty(request.getPageAliase()))) {
            Optional<List<CmsPage>> o = cmsPageRepository.findBySiteId(request.getSiteId());
            if (o.isPresent()) {
                List<CmsPage> list = o.get();
                queryResult = new QueryResult<CmsPage>();
                queryResult.setList(list);
                queryResult.setTotal((long) list.size());
                result = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
            }

        } else if ((request.getPageAliase() != null || request.getPageAliase() != "") && (request.getSiteId() == null || request.getSiteId() == "")) {
            // 根据别名查询
            System.out.println(2222);
            Optional<List<CmsPage>> o = cmsPageRepository.findByPageAliase(request.getPageAliase());
            if (o.isPresent()) {

                List<CmsPage> list = o.get();
                queryResult = new QueryResult<CmsPage>();
                queryResult.setTotal((long) list.size());
                queryResult.setList(list);

                result = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
            }

        } else if (request.getTemplateId() != null) {
            // 根据模板ID查询
            Optional<List<CmsPage>> o = cmsPageRepository.findByTemplateId(request.getTemplateId());
            System.out.println(3333);
            if (o.isPresent()) {

                List<CmsPage> cmsPages = o.get();
                queryResult = new QueryResult<>();
                queryResult.setList(cmsPages);
                queryResult.setTotal((long) cmsPages.size());

                result = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
            } else {
                result = new QueryResponseResult(CommonCode.FAIL, null);
            }


        } else if (request.getSiteId() != null && request.getPageAliase() != null) {
            // 根据站点ID和站点别名进行查询

            Optional<List<CmsPage>> o = cmsPageRepository.findBySiteIdAndPageAliase(request.getSiteId(), request.getPageAliase());
            if (o.isPresent()) {
                System.out.println(4444);
                List<CmsPage> list = o.get();
                queryResult = new QueryResult<>();
                queryResult.setList(list);
                queryResult.setTotal((long) list.size());
                result = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
            } else {
                System.out.println(5555);
                result = new QueryResponseResult(CommonCode.FAIL, null);
            }

        } else {
            // 请求参数错误 返回错误结果
            queryResult = new QueryResult<CmsPage>();
            queryResult.setList(null);
            queryResult.setTotal(-1L);
            result = new QueryResponseResult(CommonCode.FAIL, queryResult);
        }


        return result;
    }

    @Override
    public QueryResponseResult add(CmsPage cmsPage) {

        // 校验非法参数
        if (ObjectUtils.isEmpty(cmsPage)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        // 校验页面是否已经存在
        Optional<CmsPage> o = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (o.isPresent()) {
            CmsPage page = o.get();
            if (!ObjectUtils.isEmpty(page)) {
                ThrowException.exception(CmsCode.CMS_ADDPAGE_EXISTSNAME);
            }
        }

        /*将增加成功之后的CmsPage封装到返回类型中.*/
        CmsPage save = cmsPageRepository.save(cmsPage);
        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        queryResult.setData(save);

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }


    @Override
    public CmsPage addPage(CmsPage page) {


        if (ObjectUtils.isEmpty(page)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        // 校验页面是否已经存在
        Optional<CmsPage> o = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(page.getPageName(),
                page.getSiteId(), page.getPageWebPath());
        if (o.isPresent()) {
            CmsPage cmsPage = o.get();
            if (!ObjectUtils.isEmpty(cmsPage)) {
                cmsPageRepository.deleteCmsPageByPageNameAndSiteIdAndPageWebPath(page.getPageName(),
                        page.getSiteId(), page.getPageWebPath());
                this.addPage(page);
            }
        }

        /*将增加成功之后的CmsPage封装到返回类型中.*/
        CmsPage save = cmsPageRepository.save(page);
        log.info("增加之后返回的CmsPage信息:  " + save);
        return save;
    }

    @Override
    public QueryResponseResult delete(String pageID) {

        cmsPageRepository.deleteById(pageID);
        QueryResult<CmsPage> result = new QueryResult<>();
        result.setList(null);
        result.setTotal(0L);

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public QueryResponseResult update(CmsPage request, String pageID) {

        QueryResult<CmsPage> result = new QueryResult<>();
        Optional<CmsPage> o = cmsPageRepository.findById(pageID);
        if (o.isPresent()) {
            CmsPage cmsPage = o.get();

            cmsPage.setSiteId(request.getSiteId());
            cmsPage.setTemplateId(request.getTemplateId());
            cmsPage.setPageName(request.getPageName());

            if (!StringUtils.isEmpty(request.getPageAliase())) {
                cmsPage.setPageAliase(request.getPageAliase());
            }
            cmsPage.setPageWebPath(request.getPageWebPath());
            cmsPage.setPagePhysicalPath(request.getPagePhysicalPath());

            if (!StringUtils.isEmpty(request.getDataUrl())) {
                cmsPage.setDataUrl(request.getDataUrl());
            }

            if (!StringUtils.isEmpty(request.getPageType())) {
                cmsPage.setPageType(request.getPageType());
            }
            if (!StringUtils.isEmpty(request.getPageCreateTime())) {
                cmsPage.setPageCreateTime(request.getPageCreateTime());
            }

            if (!StringUtils.isEmpty(request.getDataUrl())) {
                cmsPage.setDataUrl(request.getDataUrl());
            }
            System.out.println(cmsPage);
            cmsPageRepository.save(cmsPage);
            List<CmsPage> list = new ArrayList<CmsPage>();
            list.add(cmsPage);
            result.setList(list);
            result.setTotal(1L);
        }
        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public CmsPage findByPageName(String name) {
        if (!StringUtils.isEmpty(name)) {
            //不空
            Optional<CmsPage> o = cmsPageRepository.findByPageName(name);
            if (o.isPresent()) {
                //如果 o 存在
                CmsPage cmsPage = o.get();
                if (ObjectUtils.isEmpty(cmsPage)) {
                    throw new CustomException(CmsCode.CMS_PAGE_NOT_FIND);
                } else {
                    return cmsPage;
                }
            } else {
                throw new CustomException(CommonCode.FAIL);
            }
        } else {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
            return null;
        }
    }

    @Override
    public CmsPage getByID(String id) {
        Optional<CmsPage> o = cmsPageRepository.findById(id);
        if (o.isPresent()) {
            CmsPage cmsPage = o.get();
            if (cmsPage == null) {
                ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
            }
            return cmsPage;
        }
        return null;
    }

    @Override
    public ResponseResult publishPage(String pageID) {

        if (StringUtils.isEmpty(pageID)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        CmsPage page = this.getByID(pageID);
        if (page == null) {
            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }
        String content = this.getPageHtml(pageID);
        if (StringUtils.isEmpty(content)) {
            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }

        // 将静态文件保存到GridFS上  并将保存后返回的HTML 作为值修改page的htmlFileID
        CmsPage cmsPage = this.saveHtml(pageID, content);
        cmsPageRepository.save(cmsPage);

        Map<String, Object> message = new HashMap<>();
        message.put("pageID", cmsPage.getId());
        Map<String, Object> properties = new HashMap<>();
        // 向MQ发送消息
        messageSender.sendMessage(message, properties);

        return ResponseResult.SUCCESS();
    }

    /**
     * 将该字符串所表示的HTML页面发布到GridFS上
     * <p>
     * 存储之前需要先看看pageID对应的html页面是否存在
     *
     * @param pageID
     * @param htmlContent
     * @return
     */
    @Override
    public CmsPage saveHtml(String pageID, String htmlContent) {

        CmsPage page = this.getByID(pageID);
        if (ObjectUtils.isEmpty(page)) {
            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }
        if (!StringUtils.isEmpty(page.getHtmlFileId())) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(page.getHtmlFileId())));
        }

        InputStream inputStream = null;
        try {
            inputStream = IOUtils.toInputStream(htmlContent, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectId objectId = gridFsTemplate.store(inputStream, page.getPageName());
        page.setHtmlFileId(objectId.toString());

        return page;
    }


    @Override
    public CmsPage getByPageNameAndSiteIdAndPageWebPath(String pageName, String siteID, String webPath) {

        if (StringUtils.isEmpty(pageName)) {

            return null;
        }

        if (StringUtils.isEmpty(siteID)) {

            return null;
        }

        if (StringUtils.isEmpty(webPath)) {

            return null;
        }

        Optional<CmsPage> o = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(pageName, siteID, webPath);
        CmsPage page = null;
        if (o.isPresent()) {

            page = o.get();
        }

        return page;
    }

    /**
     * @param page             第几页
     * @param size             每个页面上的数量
     * @param queryPageRequest 查询条件的接口
     * @return 查询结果
     */
    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {

        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }

        if (page <= 0) {
            page = 1;
        }
        // 为了适应MongoDB的接口 将页码-1
        page = page - 1;

        if (size <= 0) {
            size = 20;
        }

        //  分页查询
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> lists = cmsPageRepository.findAll(pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        queryResult.setList(lists.getContent());
        queryResult.setTotal((long) lists.getSize());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);

        return queryResponseResult;
    }

    /**
     * 根据模板内容和模型数据静态化
     */
    @Override
    public String getPageHtml(String pageID) {

        Map model = this.getModel(pageID);
        if (model == null) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        String templateContent = this.getTemplateContent(pageID);
        if (StringUtils.isEmpty(templateContent)) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        String pagehtml = this.generateHtml(model, templateContent);
        if (StringUtils.isEmpty(pagehtml)) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }

        return pagehtml;
    }

    public String generateHtml(Map map, String content) {

        // 生成配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 获取模板加载器
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.putTemplate("template", content);
        configuration.setTemplateLoader(loader);

        try {
            Template template = configuration.getTemplate("template");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            return html;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据templateFileID获取模板字符串
     */
    public String getTemplateContent(String pageID) {

        String fileID = this.getTemplateFileID(pageID);
        if (StringUtils.isEmpty(fileID)) {
            ThrowException.exception(CmsCode.CMS_NOT_FIND_TEMPLATE_FILE);
        }
        String templateFileID = this.getTemplateFileID(pageID);
        if (StringUtils.isEmpty(templateFileID)) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        // 取出模板文件内容
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileID)));
        if (file == null) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        // 打开下载流对象通过GridFSFile的ObjectID
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());

        // 创建GridFsResource
        GridFsResource resource = new GridFsResource(file, downloadStream);

        try {
            String content = IOUtils.toString(resource.getInputStream(), "utf-8");
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据Page上的templateID获取页面模板
     */
    public String getTemplateFileID(String pageID) {

        CmsPage cmspage = this.getByID(pageID);
        if (cmspage == null) {
            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }
        String templateID = cmspage.getTemplateId();
        if (StringUtils.isEmpty(templateID)) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(templateID);
        if (optional.isPresent()) {
            CmsTemplate template = optional.get();
            if (template == null) {
                ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
            }
            return template.getTemplateFileId();
        }

        return null;
    }


    /**
     * 根据页面获取页面模型数据
     *
     * @param pageID
     * @return
     */
    public Map getModel(String pageID) {

        CmsPage cmsPage = this.getByID(pageID);
        if (cmsPage == null) {
            ThrowException.exception(CmsCode.CMS_PAGE_NOT_FIND);
        }
        String url = cmsPage.getDataUrl();
        if (StringUtils.isEmpty(url)) {
            ThrowException.exception(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }

        ResponseEntity<Map> entity = restTemplate.getForEntity(url, Map.class);
        Map body = entity.getBody();

        return body;
    }


}
