package com.mzx.search.dao;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 16:45
 */
public interface IElasticDao {


    /**
     * 向ES中指定的索引库中增加索引信息.
     * <p>
     * 值得注意的是传进来的参数一定要先校验在传进来.
     *
     * @param coursePub
     * @return
     */
    ResponseResult addIndex(CoursePub coursePub);


    QueryResponseResult search(CourseSearchParam param);

}
