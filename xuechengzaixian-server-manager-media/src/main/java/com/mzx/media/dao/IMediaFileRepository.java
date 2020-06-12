package com.mzx.media.dao;

import com.mzx.framework.model.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/5/17 20:44
 */
public interface IMediaFileRepository extends MongoRepository<MediaFile,String> {
}
