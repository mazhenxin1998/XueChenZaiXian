package com.mzx.media.dao;

import com.mzx.framework.model.course.TeachPlanMedia;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/5/25 23:13
 */
@Mapper
@Repository
public interface ITeachPlanMediaDao {


    /**
     * 查询TeachPlanMedia所有.
     * @return
     */
    List<TeachPlanMedia> listAll();


}
