package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.ext.TeachPlanDaoReceive;
import com.mzx.framework.model.course.ext.TeachPlanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 11:27
 */
@Mapper
public interface ITeachPlanDao {

    /**
     *  根据父节点ID找出该节点下面的所有子节点信息.
     *
     *  根据父节点ID找出子节点的课程信息.
     *  并将其封装到TeachPlanDaoReceive集合中.
     * @param id
     * @return
     */
    List<TeachPlanDaoReceive> getNodeByID(@Param("id") String id);

    TeachPlanNode getNode(@Param("id") String id);

    List<TeachPlanNode> getTeachPlanNode(@Param("courseID") String courseID);




}
