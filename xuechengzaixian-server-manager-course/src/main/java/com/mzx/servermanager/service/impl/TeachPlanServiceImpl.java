package com.mzx.servermanager.service.impl;

import com.mzx.common.exception.CustomException;
import com.mzx.common.exception.ExceptionCatch;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.*;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.TeachPlan;
import com.mzx.framework.model.course.ext.TeachPlanDaoReceive;
import com.mzx.framework.model.course.ext.TeachPlanNode;
import com.mzx.framework.model.course.response.CourseCode;
import com.mzx.servermanager.dao.ICourseBaseDao;
import com.mzx.servermanager.dao.ITeachPlanDao;
import com.mzx.servermanager.service.ITeachPlanService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 14:24
 */
@Service
@Slf4j
public class TeachPlanServiceImpl implements ITeachPlanService {

    @Resource
    private ITeachPlanDao teachPlanDao;

    @Resource
    private ICourseBaseDao courseBaseDao;

    @Override
    public QueryResponseResult get(String courseID) {

        log.info("-----------------service调用成功!");
        if (StringUtils.isEmpty(courseID)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        List<TeachPlanNode> node = teachPlanDao.getTeachPlanNode(courseID);
        QueryResult result = new QueryResult();
        result.setList(node);
        result.setTotal((long) node.size());
        List<TeachPlan> teachPlanNodeParent = this.getTeachPlanNodeParent(node);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, result);
        queryResponseResult.setData(teachPlanNodeParent);
        return queryResponseResult;
    }

    @Override
    public ResponseResult add(TeachPlan teachPlan) {

        if (StringUtils.isEmpty(teachPlan.getId())) {
            teachPlan.setId(UUID.randomUUID().toString());
        }

        /*如果上传的TeachPlan上没有由根节点*/
        if (teachPlan == null || StringUtils.isEmpty(teachPlan.getCourseid()) || StringUtils.isEmpty(teachPlan.getPname())) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        String courseID = teachPlan.getCourseid();
        String parentID = teachPlan.getParentid();
        if (StringUtils.isEmpty(parentID)) {
            parentID = getTeachPlanRootID(courseID);
        }

        TeachPlan teachPlanRoot = teachPlanDao.getByID(parentID);
        // 父节点级别
        String rootGrade = teachPlanRoot.getGrade();

        //  为teachPlan设置父节点ID
        teachPlan.setParentid(parentID);
        teachPlan.setStatus("0");
        if (rootGrade.equals("1")) {
            teachPlan.setGrade("2");
        } else if (rootGrade.equals("2")) {
            teachPlan.setGrade("3");
        }

        // 设置课程ID
        teachPlan.setCourseid(teachPlanRoot.getCourseid());
        teachPlanDao.add(teachPlan);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult delete(String ID) {

        if (StringUtils.isEmpty(ID)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        try {
            teachPlanDao.delete(ID);
            return new ResponseResult(CommonCode.SUCCESS);
        } catch (Exception e) {
            log.error("DELETE FROM TeachPlan IS ERROR AND ERROR IS:" + e);
            return new ResponseResult(CommonCode.BAD_PARAMETERS);
        }

    }

    @Override
    public ResponseResult update() {
        return null;
    }

    public List<TeachPlan> getTeachPlanNodeParent(List<TeachPlanNode> node) {

        if (node == null || node.size() <= 0) {

            /*处理不当*/
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        List<TeachPlan> parentNodes = new ArrayList<>();
        // 二级节点
        List<TeachPlanNode> children = node.get(0).getChildren();
        for (int i = 0; i < children.size(); i++) {
            TeachPlan teachPlan = new TeachPlan();
            teachPlan.setId(children.get(i).getId());
            teachPlan.setPname(children.get(i).getPname());
            parentNodes.add(teachPlan);
        }

        return parentNodes;
    }

    /**
     * ----------------------------------下面是方法.
     *
     * @return
     */
    public String getTeachPlanRootID(String courseID) {
        /* 1. 根据courseID先去course_base中找出该课程ID.
         *  2. 如果该课程ID不存在根节点，那么就生成一个该课程的根节点,然后在返回该根节点的ID.
         *  3. 如果该课程有根节点那么就返回该根节点. */

        if (StringUtils.isEmpty(courseID)) {
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        CourseBase courseBase = courseBaseDao.findById(courseID);
        List<TeachPlan> teachPlanRoot = teachPlanDao.findByTeachPlanRoot(courseID);
        /*如果该root为空那么就创建一个新的root*/
        if (teachPlanRoot == null || teachPlanRoot.size() == 0) {

            TeachPlan root = new TeachPlan();
            root.setCourseid(courseID);
            root.setPname(courseBase.getName());
            root.setParentid("0");
            root.setGrade("1");
            root.setStatus("0");
            // 主键回填
            String uuid = UUID.randomUUID().toString();
            root.setId(uuid);
            teachPlanDao.add(root);
            return uuid;

        }

        return teachPlanRoot.get(0).getId();
    }


}
