package com.mzx.server.managecms.dao;

import com.mzx.framework.model.cms.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 22:34
 */
public interface StudentRepository extends MongoRepository<Student,String> {
}
