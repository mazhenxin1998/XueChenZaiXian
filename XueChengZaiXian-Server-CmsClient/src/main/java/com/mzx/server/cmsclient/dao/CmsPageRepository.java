package com.mzx.server.cmsclient.dao;

import com.mzx.framework.model.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 15:07
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
}
