package com.mzx.servermanager.service;

import com.mzx.framework.model.course.TeachPlanMediaPub;

/**
 * @author ZhenXinMa
 * @date 2020/6/1 21:34
 */
public interface ITeachPlanMediaPubService {

    /**
     * 向数据库teachPlan_Media_pub数据库中增加一条信息.
     * 保存之前应该先根据课程计划ID删除该ID所对应的所有信息,然后在保存.
     *
     * @param courseid
     */
    void saveTeachPlanMediaPub(String courseid);

    /**
     * 根据课程计划ID删除一条TeachPlanMediaPub信息.
     *
     * @param teachPlanId
     */
    void deleteTeachPlanMediaPub(String teachPlanId);

    /**
     * 根据课程计划ID查询出一条TeachPlanMediaPub信息.
     *
     * @param teachPlanID
     * @return
     */
    TeachPlanMediaPub findByTeachPlanID(String teachPlanID);

}
