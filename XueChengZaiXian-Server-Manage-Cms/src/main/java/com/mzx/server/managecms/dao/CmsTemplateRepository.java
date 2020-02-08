package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:11
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {
}
