package com.mzx.freemaker.dao;

import com.mzx.bean.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ZhenXinMa
 * @date 2020/2/15 22:21
 */
public interface ITestRepository extends MongoRepository<Student,String> {
}
