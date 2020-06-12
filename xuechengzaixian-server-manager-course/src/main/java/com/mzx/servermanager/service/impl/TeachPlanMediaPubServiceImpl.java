package com.mzx.servermanager.service.impl;

import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.framework.model.course.TeachPlanMediaPub;
import com.mzx.servermanager.dao.ITeachPlanMediaDao;
import com.mzx.servermanager.dao.ITeachPlanMediaPubDao;
import com.mzx.servermanager.service.ITeachPlanMediaPubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/6/1 21:34
 */
@Slf4j
@Service
public class TeachPlanMediaPubServiceImpl implements ITeachPlanMediaPubService {

    @Resource
    private ITeachPlanMediaPubDao iTeachPlanMediaPubDao;

    @Resource
    private ITeachPlanMediaDao iTeachPlanMediaDao;


    @Override
    public void saveTeachPlanMediaPub(String courseid) {

        if (StringUtils.isEmpty(courseid)) {

            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        // 增加之前先删除courseid所对应的信息TeachPlanMedia  删除的时候也是删除的多个.
        iTeachPlanMediaPubDao.delete(courseid);
        /*明天继续吧...*/
        List<TeachPlanMedia> mediaList = iTeachPlanMediaDao.findByCourseID(courseid);
        if (mediaList.size() == 0) {

            //
        }
        for (TeachPlanMedia planMedia : mediaList) {

            // 需要将每一个查询出来的TeachPlanMedia转换为TeachPlanMediaPub存入数据库中.
            TeachPlanMediaPub pub = new TeachPlanMediaPub();
            pub.setTeachplan_id(planMedia.getTeachplanId());
            pub.setTimestamp(LocalDate.now().toString());
            pub.setCourseid(planMedia.getCourseid());
            pub.setMedia_fileoriginalname(planMedia.getMediaFileoriginalname());
            pub.setMedia_id(planMedia.getMediaId());
            // pub.mediaUrl 应该是相对路径.
            pub.setMedia_url(planMedia.getMediaUrl());
            /*是在课程发布后就存入.*/
            iTeachPlanMediaPubDao.insert(pub);
            System.out.println(1);
        }

    }

    @Override
    public void deleteTeachPlanMediaPub(String teachPlanId) {

    }

    @Override
    public TeachPlanMediaPub findByTeachPlanID(String teachPlanID) {
        return null;
    }
}
