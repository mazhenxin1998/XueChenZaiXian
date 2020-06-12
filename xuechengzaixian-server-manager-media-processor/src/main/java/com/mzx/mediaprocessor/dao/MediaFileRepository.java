package com.mzx.mediaprocessor.dao;

import com.mzx.framework.model.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/5/19 17:47
 */
public interface MediaFileRepository extends MongoRepository<MediaFile,String> {
}
