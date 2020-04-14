package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.CoursePic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/4/7 16:22
 */
@Mapper
public interface ICoursePicDao {

    /**
     * 向course_pic数据库中增加课程对应的图片.
     * 每次新增加默认为用户要使用的课程图片，所以对于用户来说status默认是1.
     * @param courseID
     * @param url
     * @param fileName
     */
    void add(@Param(value = "courseID") String courseID,
             @Param(value = "url") String url,
             @Param(value = "fileName") String fileName,
             @Param(value = "status") String status);

    /**
     * 根据课程ID和图片名字删除指定的图片.
     * @param courseID
     * @param fileName
     */
    void delete(@Param(value = "courseID") String courseID,
                @Param(value = "fileName") String fileName);

    /**
     * 对当前未使用的图片进行批量更新.
     * @param coursePics
     */
    void update(List<CoursePic> coursePics);

    /**
     * 通过课程ID映射出课程图片的相关信息.
     * @param courseID
     * @return
     */
    List<CoursePic> getListByCourseID(@Param(value = "courseID") String courseID);

    /**
     * 查询出当前课程正在使用的课程图片.
     * @param courseID
     * @return
     */
    List<CoursePic> getCoursePicUsing(@Param(value = "courseID") String courseID);





}
