package com.mzx.servermanager.service.impl;


import com.mzx.common.model.request.RequestData;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.CourseMarket;
import com.mzx.framework.model.course.CoursePic;
import com.mzx.framework.model.course.CourseView;
import com.mzx.framework.model.course.ext.CourseInfo;
import com.mzx.framework.model.course.ext.TeachPlanNode;
import com.mzx.framework.model.course.response.CmsPostPageResult;
import com.mzx.framework.model.course.response.CourseCode;
import com.mzx.framework.model.course.response.CoursePublishResult;
import com.mzx.servermanager.dao.*;
import com.mzx.servermanager.feign.CmsPagePreviewServiceOpenFeign;
import com.mzx.servermanager.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author ZhenXinMa
 * @date 2020/4/2 9:40
 */
@Service
@Slf4j
public class CourseServiceImpl implements ICourseService {

    @Resource
    private ICourseDao courseDao;

    @Resource
    private ICourseBaseDao courseBaseDao;

    @Resource
    private ICoursePicDao coursePicDao;

    @Resource
    private ICourseMarketDao courseMarketDao;

    @Resource
    private ITeachPlanDao teachPlanDao;

    @Value("${xuechengzaixian.course.publish.dataUrlPre}")
    private String publish_dataUrlPre;

    @Value("${xuechengzaixian.course.publish.pagePhysicalPath}")
    private String publish_page_physicalpath;

    @Value("${xuechengzaixian.course.publish.pageWebPath}")
    private String publish_page_webpath;

    @Value("${xuechengzaixian.course.publish.siteId}")
    private String publish_siteId;

    @Value("${xuechengzaixian.course.publish.templateId}")
    private String publish_templateId;

    @Value(value = "${xuechengzaixian.course.publish.previewUrl}")
    private String previewUrl = "";

    @Resource
    private CmsPagePreviewServiceOpenFeign cmsClient;

    @Override
    @Cacheable(value = "courseService", key = "'getCourseLimit'+#page")
    public QueryResponseResult get(int page, int size) {

        page = page - 1;
        /*Limit分页查询的意思是几行几行*/
        if (page < 0) {

            page = 0;
        }

        page = page * size;

        if (size <= 0) {

            size = 10;
        }

        Integer count = courseDao.getCount();
        List<CourseInfo> list = courseDao.getPageInfo(page, size);
        /*info 表示的 课程ID唯一的 pic表 通过courseDao.getPageInfo() 查询出来的 不包含PIC 地址*/
        for (CourseInfo courseInfo : list) {
            /*  通过该循环对 list 中的每一项的 PIC 图片地址 进行更新.*/
            List<CoursePic> picUsing = coursePicDao.getCoursePicUsing(courseInfo.getId());
            //  如果为空 则说明 没有正在使用的图片
            if (picUsing.size() <= 0 || picUsing == null) {
                courseInfo.setPic("");
                /*continue为结束当前次循环  break为结束整个for循环*/
                continue;
            }
            if (picUsing.get(0).getPic() == null || "".equals(picUsing.get(0).getPic())) {
                courseInfo.setPic("");
                continue;
            }
            courseInfo.setPic(picUsing.get(0).getPic());

        }

        QueryResult result = new QueryResult();
        result.setList(list);
        result.setTotal(count.longValue());

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public ResponseResult add(CourseBase courseBase) {
        return null;
    }

    @Override
    public QueryResponseResult get(String ID) {

        if (StringUtils.isEmpty(ID)) {
            return new QueryResponseResult(CommonCode.BAD_PARAMETERS, null);
        }
        CourseBase courseBase = courseBaseDao.findById(ID);
        QueryResult result = new QueryResult();
        result.setData(courseBase);
        result.setTotal((long) 1);

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseResult update(CourseBase courseBase) {
        /*Boot中开启事务支持需要手动回滚异常*/

        if (StringUtils.isEmpty(courseBase.getId())) {
            return new ResponseResult(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        String id = courseBase.getId();
        /*思路一：可以根据ID删除原先的课程基本信息 然后在添加相同ID的数据可能没有被修改也可能被修改了.
         * 注意：异常如果自己捕捉，框架则不会捕捉到.*/
        courseBaseDao.delete(id);
        courseBaseDao.add(courseBase);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseResult addCoursePhoto(RequestData requestData) {

        if (StringUtils.isEmpty(requestData.getCourseID())) {
            return new ResponseResult(CommonCode.BAD_PARAMETERS);
        }
        if (StringUtils.isEmpty(requestData.getFileName())) {
            return new ResponseResult(CommonCode.BAD_PARAMETERS);
        }
        if (StringUtils.isEmpty(requestData.getUrl())) {
            return new ResponseResult(CommonCode.BAD_PARAMETERS);
        }
        String courseID = requestData.getCourseID();
        String fileName = requestData.getFileName();
        String url = requestData.getUrl();
        List<CoursePic> coursePics = coursePicDao.getListByCourseID(courseID);
        /*需要吧coursePics集合里面的课程图片的状态码全部修改为0 表示未使用.*/
        if (coursePics.size() <= 0 || coursePics == null) {
            coursePicDao.add(courseID, url, fileName, "1");
            return new ResponseResult(CommonCode.SUCCESS);
        }
        coursePicDao.update(coursePics);
        // 当将所有的status更新为0时 在将新的图片信息放入数据库.
        coursePicDao.add(courseID, url, fileName, "1");
        System.out.println(1);
        return new ResponseResult(CommonCode.SUCCESS);
//        try {
//            List<CoursePic> coursePics = coursePicDao.getListByCourseID(courseID);
//            /*需要吧coursePics集合里面的课程图片的状态码全部修改为0 表示未使用.*/
//            coursePicDao.update(coursePics);
//            // 当将所有的status更新为0时 在将新的图片信息放入数据库.
//            coursePicDao.add(courseID,url,fileName,"1");
//            return new ResponseResult(CommonCode.SUCCESS);
//        } catch (Exception e) {
//            log.error("--------------向Course_pic中增加信息出错. 具体信息 CourseServiceImpl.java 第119行");
//            return new ResponseResult(CommonCode.SERVER_ERROR);
//        }

    }

    @Override
    public QueryResponseResult getCoursePicList(String courseID) {

        if (StringUtils.isEmpty(courseID)) {
            return new QueryResponseResult(CommonCode.BAD_PARAMETERS, null);
        }
        List<CoursePic> coursePicList = coursePicDao.getListByCourseID(courseID);
        if (coursePicList.size() <= 0 || coursePicList == null) {
            return new QueryResponseResult(CourseCode.COURSE_PIC_IS_EMPTY, null);
        }
        QueryResult result = new QueryResult();
        result.setList(coursePicList);
        result.setTotal((long) coursePicList.size());

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public QueryResponseResult getCoursePicUsing(String courseID) {

        if (StringUtils.isEmpty(courseID)) {
            return new QueryResponseResult(CommonCode.BAD_PARAMETERS, null);
        }
        List<CoursePic> coursePicUsing = coursePicDao.getCoursePicUsing(courseID);
        if (coursePicUsing.size() != 1) {
            return new QueryResponseResult(CommonCode.BAD_PARAMETERS, null);
        }
        QueryResult result = new QueryResult();
        result.setList(coursePicUsing);
        result.setTotal(1L);

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public CourseView courseView(String id) {

        if (StringUtils.isEmpty(id)) {

            throw new RuntimeException();
            // return new QueryResponseResult(CommonCode.BAD_PARAMETERS,null);
        }

        /*ID不再为空.*/
        try {

            TeachPlanNode teachPlanNode = teachPlanDao.getNode(id);
            CourseBase courseBase = courseBaseDao.findById(id);
            CourseMarket courseMarket = courseMarketDao.getByID(id);
            List<CoursePic> coursePic = coursePicDao.getCoursePicUsing(id);
            if (coursePic.size() <= 1) {

                CourseView view = new CourseView();
                view.setCourseBase(courseBase);
                view.setCourseMarket(courseMarket);
                view.setTeachPlanNode(teachPlanNode);
                if (coursePic == null) {
                    view.setCoursePic(null);
                } else {

                    if (coursePic.size() == 0) {

                        view.setCoursePic(null);
                    } else {

                        view.setCoursePic(coursePic.get(0));
                    }

                }

                return view;
            } else {

                return null;
            }

        } catch (Exception e) {

            log.info("----------CourseServiceImpl courseView 发生错误 Message : " + e.getMessage());
            System.out.println(e);
            return null;
        }

    }

    @Override
    @Cacheable(value = "courseService", key = "'preview'+#id")
    public CoursePublishResult preview(String id) {

        CourseBase courseBase = courseBaseDao.findById(id);
        /*参数是课程ID*/
        CmsPage page = new CmsPage();
        page.setPageWebPath(publish_page_webpath);
        page.setSiteId(publish_siteId);
        page.setTemplateId(publish_templateId);
        page.setPagePhysicalPath(publish_page_physicalpath);
        page.setPageName(id + ".html");
        page.setPageAliase(courseBase.getName());
        String dataUrl = publish_dataUrlPre + id;
        page.setDataUrl(dataUrl);

        String url = "";
        try {

            CmsPage result = cmsClient.addPage(page);
            /* 这里需要处理下页面已经存在的情况
             * findByPageNameAndSiteIdAndPageWebPath*/
            /*  不知道能不能转成CmsPage*/

            url = previewUrl + result.getId();
        } catch (Exception e) {

            /*有问题还.*/
            CmsPage cmsPage = cmsClient.getByName(page.getPageName());
            url = previewUrl + cmsPage.getId();
        }

        System.out.println(url);
        return new CoursePublishResult(CommonCode.SUCCESS, url);
    }

    @Override
    @Cacheable(value = "courseService", key = "'courseCount'")
    public Integer count() {
        /*condition存入缓存的判断条件
         * 测试缓存*/
        Integer count = courseDao.getCount();
        return count;
    }

    @Override
    public CoursePublishResult publish(String courseID) {

        CourseBase courseBase = courseBaseDao.findById(courseID);
        /*发布课程详情页面
         * 发布之后更新课程的状态*/
        CmsPostPageResult cmsPostPageResult = this.publishPageOfficial(courseID);
        CourseBase cb = this.updateCourseStatus(courseBase);
        /*页面URL*/
        String pageUrl = cmsPostPageResult.getPageUrl();
        return new CoursePublishResult(CommonCode.SUCCESS,pageUrl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CourseBase updateCourseStatus(CourseBase courseBase) {

        /*课程发布状态更新.
         * 先删除在增加*/
        courseBase.setStatus("202002");
        courseBaseDao.delete(courseBase.getId());
        courseBaseDao.add(courseBase);
        return courseBase;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CmsPostPageResult publishPageOfficial(String courseID) {

        /*根据课程ID生成一个CmsPage页面.*/
        CourseBase courseBase = courseBaseDao.findById(courseID);
        /*发布课程预览页面*/
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(publish_siteId);
        cmsPage.setTemplateId(publish_templateId);
        cmsPage.setPageName(this.appendString(courseID, ".html"));
        /*页面别名就是courseBase的名字*/
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageWebPath(publish_page_webpath);
        /*页面存储路径*/
        cmsPage.setPagePhysicalPath(publish_page_physicalpath);
        /*数据URL*/
        cmsPage.setDataUrl(this.appendString(publish_dataUrlPre,courseID));

        /*页面调用远程服务进行页面发布
        * 该CmsPage是没有ID的*/
        CmsPostPageResult postPageResult = cmsClient.postPageQuick(cmsPage);

        return postPageResult;
    }


    @Override
    public synchronized String appendString(String... args) {
        /*同时只能调用一次*/
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {

            builder.append(arg);
        }

        return builder.toString();
    }


}
