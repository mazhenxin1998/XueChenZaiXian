package com.mzx.learning.service;

import com.mzx.framework.model.learning.response.GetMediaResult;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 22:50
 */
public interface ICourseLearningService {

    /**
     * 获取courseid对应课程的第teachPlanID小节的媒资信息.
     * @param courseid
     * @param teachPlanID
     * @return
     */
    GetMediaResult getMedia(String courseid,String teachPlanID);

}
