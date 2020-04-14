package com.mzx.servermanager.dao;

import com.github.pagehelper.Page;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.ext.CourseInfo;
import com.mzx.framework.model.course.requesed.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/4/2 9:42
 */
@Mapper
public interface ICourseDao {

        List<CourseInfo> getInfo(@Param(value = "request") CourseListRequest request);

        Page<CourseBase> get();

        /**
         * @Cacheable(value = "course", key = "'getPageInfo'+#page").
         * @param page
         * @param size
         * @return
         */
        List<CourseInfo> getPageInfo(@Param(value = "page") Integer page,
                                     @Param(value = "size") Integer size);

        /**
         * 查询当前用户所拥有的所有课程.
         *
         *         @Cacheable(value = "course").
         * 使用SpringBoot的缓存的时候,如果没有指定Key应该是以方法名字为Key.
         * @return
         */
        Integer getCount();

}