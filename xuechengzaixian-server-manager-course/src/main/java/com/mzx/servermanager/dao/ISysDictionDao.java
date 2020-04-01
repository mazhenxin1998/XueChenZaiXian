package com.mzx.servermanager.dao;

import com.mzx.framework.model.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 19:52
 */
public interface ISysDictionDao extends MongoRepository<SysDictionary,String> {

    Optional<SysDictionary> findBydName(String DName);

    Optional<SysDictionary> findBydType(String DType);

}
