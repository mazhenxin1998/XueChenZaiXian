package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * @author ZhenXinMa
 * @date 2020/2/5 21:50
 *
 *      CmsPage:  当前结果返回的实体类
 *      String :  当前实体类的组件ID类型
 *
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
}
