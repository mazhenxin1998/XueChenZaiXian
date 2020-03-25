package com.mzx.servermanager.service.impl;

import com.mzx.common.exception.CustomException;
import com.mzx.common.exception.ExceptionCatch;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.*;
import com.mzx.framework.model.course.TeachPlan;
import com.mzx.framework.model.course.ext.TeachPlanDaoReceive;
import com.mzx.framework.model.course.ext.TeachPlanNode;
import com.mzx.framework.model.course.response.CourseCode;
import com.mzx.servermanager.dao.ITeachPlanDao;
import com.mzx.servermanager.service.ITeachPlanService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 14:24
 */
@Service
@Slf4j
public class TeachPlanServiceImpl implements ITeachPlanService {

    @Resource
    private ITeachPlanDao teachPlanDao;

    @Override
    public QueryResponseResult get(String courseID){

        log.info("-----------------service调用成功!");
        if( StringUtils.isEmpty(courseID) ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        List<TeachPlanNode> node = teachPlanDao.getTeachPlanNode(courseID);
        QueryResult result = new QueryResult();
        result.setList(node);
        result.setTotal((long) node.size());
        List<TeachPlan> teachPlanNodeParent = this.getTeachPlanNodeParent(node);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,result);
        queryResponseResult.setData(teachPlanNodeParent);
        return queryResponseResult;
    }

    @Override
    public ResponseResult add(TeachPlan teachPlan) {
        /*未完成的业务代码*/
        return null;
    }

    public List<TeachPlan> getTeachPlanNodeParent(List<TeachPlanNode> node){

        if( node == null || node.size()<= 0 ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        List<TeachPlan> parentNodes = new ArrayList<>();
        // 二级节点
        List<TeachPlanNode> children = node.get(0).getChildren();
        for( int i=0;i<children.size();i++ ){
            TeachPlan teachPlan = new TeachPlan();
            teachPlan.setId(children.get(i).getId());
            teachPlan.setPname(children.get(i).getPname());
            parentNodes.add(teachPlan);
        }

        return parentNodes;
    }

}
