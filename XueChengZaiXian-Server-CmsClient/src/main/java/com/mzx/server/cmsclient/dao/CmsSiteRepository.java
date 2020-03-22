package com.mzx.server.cmsclient.dao;

import com.mzx.framework.model.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/3/1 15:08
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
