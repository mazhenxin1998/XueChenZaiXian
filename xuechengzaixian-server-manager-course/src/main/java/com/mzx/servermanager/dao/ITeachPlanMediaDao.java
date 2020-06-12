package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.TeachPlanMedia;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/5/26 20:45
 */
@Mapper
@Repository
public interface ITeachPlanMediaDao {

    /**
     * 通过课程计划ID查询出唯一的TeachPlanMedia.
     *
     * @param teachPlanId
     * @return
     */
    TeachPlanMedia findById(String teachPlanId);

    /**
     * 通过课程计划ID删除媒资与课程相互关联的信息.
     *
     * @param teachPlanId
     */
    void deleteById(String teachPlanId);

    /**
     * 向数据库teachplan_media增加TeachPlanMedia.
     *
     * @param teachPlanMedia
     */
    void addTeachPlanMedia(@Param(value = "teachPlanMedia") TeachPlanMedia teachPlanMedia);

    /**
     * 根据课程ID查询出所有的TeachPlanMedia信息.
     *
     * @param courseID
     * @return
     */
    List<TeachPlanMedia> findByCourseID(String courseID);


}
