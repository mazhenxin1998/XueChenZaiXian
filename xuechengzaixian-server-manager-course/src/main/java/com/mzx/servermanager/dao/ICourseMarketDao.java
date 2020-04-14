package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.CourseMarket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhenXinMa
 * @date 2020/4/3 21:36
 */
@Mapper
public interface ICourseMarketDao {

    CourseMarket getByID(String id);

    void delete(@Param("id") String id);

    void add(@Param("courseMarket") CourseMarket courseMarket);


}
