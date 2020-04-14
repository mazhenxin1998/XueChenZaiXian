package com.mzx.servermanager.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseMarket;

/**
 * @author ZhenXinMa
 * @date 2020/4/3 21:32
 */
public interface ICourseMarketService {

    /**
     * 根据课程id查询该课程的的营销信息.
     * @param id
     * @return
     */
    QueryResponseResult get(String id);

    /**
     * 对该课程的销售信息进行更新.
     * @param courseMarket
     * @return
     */
    ResponseResult update(CourseMarket courseMarket);


}
