package com.mzx.servermanager.controller;

import com.mzx.api.course.ICourseControllerApi;
import com.mzx.common.model.request.RequestData;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.CourseView;
import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.framework.model.course.ext.CourseInfo;
import com.mzx.framework.model.course.response.CoursePublishResult;
import com.mzx.servermanager.dao.ICourseDao;
import com.mzx.servermanager.feign.CmsPagePreviewServiceOpenFeign;
import com.mzx.servermanager.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 新增课程往course_pic 里面放，为了我的课程首页显示我的课程列表.此时课程courseID不能重复.
 * <p>
 * 新增表course_pics 里面放图片的URL地址.
 *
 * @author ZhenXinMa
 * @date 2020/4/2 9:39
 */
@RestController
@RequestMapping(value = "/course")
@Slf4j
public class CourseController implements ICourseControllerApi {

    @Resource
    private ICourseService courseService;

    @Resource
    private ICourseDao courseDao;

    @Resource
    private CmsPagePreviewServiceOpenFeign cmsClient;

    @GetMapping(value = "/cache/t")
    public String t1() {
        Integer count = courseService.count();
        return count.toString();
    }

    @GetMapping(value = "/cache/t/{page}/{size}")
    public Object t2(@PathVariable(value = "page") Integer page,
                     @PathVariable(value = "size") Integer size) {
        List<CourseInfo> pageInfo = courseDao.getPageInfo(page, size);
        return pageInfo;
    }

    /**
     * 查询的时候需要有用户凭证.
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    @GetMapping(value = "/list/get")
    public QueryResponseResult get(@RequestParam(required = true, defaultValue = "1") int page,
                                   @RequestParam(required = true, defaultValue = "7") int size) {
        return courseService.get(page, size);
    }

    @Override
    @GetMapping(value = "/get/{ID}")
    public QueryResponseResult get(@PathVariable(value = "ID") String ID) {
        log.info("---------------------------CourseController  Get Course By  ID发生了");
        return courseService.get(ID);
    }

    @Override
    @PostMapping(value = "/baseInfo/update")
    public ResponseResult update(@RequestBody CourseBase courseBase) {
        log.info("------------------------CourseController Post update 发生了 ");
        return courseService.update(courseBase);
    }

    @Override
    @PostMapping(value = "/add/photo")
    public ResponseResult addCoursePhoto(@RequestBody RequestData requestData) {
        log.info("-------------------data    " + requestData);
        return courseService.addCoursePhoto(requestData);
    }

    @Override
    @GetMapping(value = "/get/coursePicList/{courseID}")
    public QueryResponseResult getCoursePicList(@PathVariable(value = "courseID") String courseID) {
        return courseService.getCoursePicList(courseID);
    }

    @Override
    @GetMapping(value = "/get/coursePicUsing/{courseID}")
    public QueryResponseResult getCoursePicUsing(@PathVariable(value = "courseID") String courseID) {
        return courseService.getCoursePicUsing(courseID);
    }


    @Override
    @GetMapping(value = "/courseView/{id}")
    public CourseView courseView(@PathVariable(value = "id") String id) {

        return courseService.courseView(id);
    }

    @Override
    @GetMapping(value = "/preview/{id}")
    public CoursePublishResult coursePreview(@PathVariable(value = "id") String id) {

        return courseService.preview(id);
    }

    @Override
    @GetMapping(value = "/publish/{courseID}")
    public CoursePublishResult publish(@PathVariable(value = "courseID") String courseID) {
        log.info("课程发布接口    "+courseID);
        /* 课程发布.
        *调用的是Service的publish方法*/
        return courseService.publish(courseID);
    }

    @Override
    @PostMapping(value = "/savemedia")
    public ResponseResult addMedia(@RequestBody TeachPlanMedia teachPlanMedia) {

        // 前台传过来的teachPlanMeida就不是相对路径.
        System.out.println(teachPlanMedia.toString());
        /*
        * 保存Media信息向teachPlan_media 是在选择视频之后才保存的.*/
        return courseService.addMedia(teachPlanMedia);
    }

    /**
     * 增加课程的时候需要有用户凭证.
     *
     * @param courseBase
     * @return
     */
    @Override
    public ResponseResult add(CourseBase courseBase) {
        return null;
    }
}
