package com.mzx.servermanager.service;

import com.mzx.common.model.request.RequestData;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.CourseView;
import com.mzx.framework.model.course.response.CoursePublishResult;

/**
 * @author ZhenXinMa
 * @date 2020/4/2 9:40
 */
public interface ICourseService {

    /**
     * 分页查询后台管理中心我的课程列表页面.
     *
     * 具体的课程列表从course_base查询,课程图片从course_pics查询,采用一一配对方式.
     * @param page
     * @param size
     * @return
     */
    QueryResponseResult get(int page, int size);

    ResponseResult add(CourseBase courseBase);

    QueryResponseResult get(String ID);

    /**
     * 课程基本信息修改.
     *
     * @param courseBase
     * @return
     */
    ResponseResult update(CourseBase courseBase);

    /**
     * 为课程设置课程图片.
     *
     * @param requestData
     * @return
     */
    ResponseResult addCoursePhoto(RequestData requestData);

    /**
     * 查询该课程所使用的课程图片.
     *
     * @param courseID
     * @return
     */
    QueryResponseResult getCoursePicList(String courseID);

    /**
     * 查询课程正在使用的图片.
     *
     * @param courseID
     * @return
     */
    QueryResponseResult getCoursePicUsing(String courseID);

    /**
     * 获取到到所传参数课程ID的所有数据.
     * <p>
     * 通过课程ID,查询到该课程的基本信息,以及营销信息,以及图片和课程计划等.
     *
     * @param id courseID.
     * @return 将该课程的基本信息、营销信息、该课程的图片、课程计划等封装成CourseView装入到QueryResponseResult.
     */
    CourseView courseView(String id);

    /**
     * 课程预览的具体实现.
     * <p>
     * 使用OpenFeign远程调用CMS服务,使用CMS服务的页面静态化技术将参数对应的ID进行页面静态化.
     *
     * @param id 课程ID.
     * @return 返回的是页面预览的URL地址.
     */
    CoursePublishResult preview(String id);

    Integer count();


}
