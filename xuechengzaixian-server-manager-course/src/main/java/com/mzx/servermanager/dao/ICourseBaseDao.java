package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.CourseBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhenXinMa
 * @date 2020/3/26 10:45
 */
@Mapper
public interface ICourseBaseDao {

    CourseBase findById(@Param("id") String id);

    void add(@Param(value = "courseBase") CourseBase courseBase);



}
