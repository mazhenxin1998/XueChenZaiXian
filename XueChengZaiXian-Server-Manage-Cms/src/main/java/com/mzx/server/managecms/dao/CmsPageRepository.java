package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


/**
 * @author ZhenXinMa
 * @date 2020/2/5 21:50
 *
 *      CmsPage:  当前结果返回的实体类
 *      String :  当前实体类的组件ID类型
 *
 *      SpringDataMongoDB 底层是JDK代理
 *
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

    Optional<CmsPage> findByPageName(String pageName);

    /**
     * 根据siteID 进行精确查询
     * @param siteId    查询条件
     * @return
     */
    Optional<List<CmsPage>> findBySiteId(String siteId);

    /**
     * 根据模板ID进行精确查询
     * @param templateID
     * @return
     */
    Optional<List<CmsPage>> findByTemplateId(String templateID);

    /**
     *  根据页面的别名进行模糊查询
     * @param pageAlias   查询条件
     * @return
     */
    Optional<List<CmsPage>> findByPageAliase(String pageAlias);


    Optional<List<CmsPage>> findBySiteIdAndPageAliase(String siteId,String pageAliase);

    Optional<CmsPage> findById(String Id);



}
