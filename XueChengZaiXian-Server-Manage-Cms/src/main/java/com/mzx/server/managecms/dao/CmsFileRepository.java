package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.CmsFile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 22:14
 */
public interface CmsFileRepository extends MongoRepository<CmsFile,String> {

    List<CmsFile> findByFilename(String name);

}
