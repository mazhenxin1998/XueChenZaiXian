package com.mzx.learning.service.impl;

import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.framework.model.course.TeachPlanMediaPub;
import com.mzx.framework.model.learning.response.GetMediaResult;
import com.mzx.framework.model.learning.response.LearningCode;
import com.mzx.learning.service.ICourseLearningService;
import com.mzx.learning.service.feign.ISearchServerClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 22:51
 */
@Service
public class CourseLearningServiceImpl implements ICourseLearningService {

    @Resource
    private ISearchServerClient searchServerClient;


    @Override
    public GetMediaResult getMedia(String courseid, String teachPlanID) {

        if (StringUtils.isEmpty(teachPlanID)) {

            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        TeachPlanMediaPub mediaPub = searchServerClient.getMedia(teachPlanID);
        if (mediaPub == null) {

            ThrowException.exception(LearningCode.LEARNING_GETMEDIA_ERROR);
        }

        return new GetMediaResult(CommonCode.SUCCESS, mediaPub.getMedia_url());

    }
}
