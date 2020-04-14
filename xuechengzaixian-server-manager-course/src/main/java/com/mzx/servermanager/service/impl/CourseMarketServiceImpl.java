package com.mzx.servermanager.service.impl;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CourseMarket;
import com.mzx.servermanager.dao.ICourseMarketDao;
import com.mzx.servermanager.service.ICourseMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/3 21:33
 */
@Service
@Slf4j
public class CourseMarketServiceImpl implements ICourseMarketService {

    @Resource
    private ICourseMarketDao courseMarketDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public QueryResponseResult get(String id) {

        if(StringUtils.isEmpty(id)){
            return new QueryResponseResult(CommonCode.BAD_PARAMETERS,null);
        }
        CourseMarket market = courseMarketDao.getByID(id);
        QueryResult result = new QueryResult();
        result.setData(market);
        result.setTotal(1L);

        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult update(CourseMarket courseMarket) {

        if( StringUtils.isEmpty(courseMarket.getId())){
            return new ResponseResult(CommonCode.BAD_PARAMETERS);
        }
        String courseID = courseMarket.getId();
        /*更新课程营销思路：先根据课程ID删除相对应的营销计划，增加一个相对应的课程营销*/
        courseMarketDao.delete(courseID);
        courseMarketDao.add(courseMarket);
        log.info("-------------------------CourseMarket信息修改成功");
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
