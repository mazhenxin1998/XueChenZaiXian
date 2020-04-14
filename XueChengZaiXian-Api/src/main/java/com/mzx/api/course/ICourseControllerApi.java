package com.mzx.api.course;

import com.mzx.common.model.request.RequestData;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.CourseView;
import com.mzx.framework.model.course.ext.CourseInfo;
import com.mzx.framework.model.course.response.CoursePublishResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 22:05
 */
@Api(value = "课程管理接口")
public interface ICourseControllerApi {

    /**
     * 分页查询出当前用户(老师或者用户)对用的课程.
     * @return
     */
    @ApiOperation(value = "分页查询")
    QueryResponseResult get(int page,int size);

    @ApiOperation(value = "增加我的课程")
    ResponseResult add(CourseBase courseBase);

    @ApiOperation(value = "根据课程ID查询该课程的基本信息")
    QueryResponseResult get(String ID);

    @ApiOperation(value = "课程基本信息修改")
    ResponseResult update(CourseBase courseBase);

    @ApiOperation(value = "上传课程图片的")
    ResponseResult addCoursePhoto(RequestData data);

    @ApiOperation(value = "根据课程ID查询出该课程所拥有的所有图片")
    QueryResponseResult getCoursePicList(String courseID);

    @ApiOperation(value = "查询当前课程正在使用的图片")
    QueryResponseResult getCoursePicUsing(String courseID);

    /**
     * 根据页面id进行预览.
     *
     * 首先需要根据课程ID获取到该课程ID所对应的静态页面所需要的数据,再利用freemaker静态化技术进行将数据静态化.
     * 这里和CmsPage页面预览类似.
     * @param id
     * @return
     */
    @ApiOperation(value = "预览课程ID为id的课程")
    CourseView courseView(String id);

    /**
     * 课程预览的接口,返回的是该课程页面的URL地址,并将其封装在CoursePublishResult中.
     * @param id 课程ID.
     * @return 页面预览后打的结果,其中页面URL被封装在return中.
     */
    @ApiOperation(value = "课程预览接口")
    CoursePublishResult coursePreview(String id);


}
