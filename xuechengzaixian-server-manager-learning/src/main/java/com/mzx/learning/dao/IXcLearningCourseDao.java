package com.mzx.learning.dao;

import com.mzx.framework.model.learning.XcLearningCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 17:17
 */
@Mapper
public interface IXcLearningCourseDao {

    /**
     * 测试查询所有的学习课程.
     * @return
     */
    List<XcLearningCourse> listAll();

}
