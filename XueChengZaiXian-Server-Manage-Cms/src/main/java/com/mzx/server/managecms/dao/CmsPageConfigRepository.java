package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:38
 */
public interface CmsPageConfigRepository extends MongoRepository<CmsConfig,String> {


    @Override
    Optional<CmsConfig> findById(String id);

}
