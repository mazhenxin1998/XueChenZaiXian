package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.TeachPlanMediaPub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZhenXinMa
 * @date 2020/6/1 21:21
 */
@Mapper
public interface ITeachPlanMediaPubDao {

    /**
     * 根据课程id查询出一个TeachPlanMediaPub.
     *
     * @param id
     * @return
     */
    TeachPlanMediaPub findById(String id);

    /**
     * 向数据库中增加一个信息.
     * @param teachPlanMediaPub
     */
    void insert(@Param(value = "teachPlanMediaPub") TeachPlanMediaPub teachPlanMediaPub);

    /**
     * 根据课程计划ID删除一条TeachPlanMediaPub信息.
     * @param teachPlanId
     */
    void delete(String teachPlanId);


}
