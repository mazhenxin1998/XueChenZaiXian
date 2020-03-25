package com.mzx.servermanager.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.TeachPlan;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 14:24
 */
public interface ITeachPlanService {

    QueryResponseResult get(String courseID);

    ResponseResult add(@RequestBody TeachPlan teachPlan);

}
