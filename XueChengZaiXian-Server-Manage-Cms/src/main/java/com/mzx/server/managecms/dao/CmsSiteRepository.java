package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @author ZhenXinMa
 * @date 2020/2/8 15:53
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {


}
