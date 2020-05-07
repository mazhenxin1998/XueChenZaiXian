package com.mzx.search.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;
import org.elasticsearch.search.SearchHit;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 16:43
 */
public interface IElasticService {


    /**
     * 向ES中指定的索引库中增加索引信息.
     * <p>
     * 值得注意的是传进来的参数一定要先校验在传进来.
     *
     * @param coursePub
     * @return
     */
    ResponseResult addIndex(@NotNull CoursePub coursePub);


    /**
     * 根据关键字查询.
     * <p>
     * 结果高亮显示.
     *
     * @param param
     * @return
     */
    QueryResponseResult searchKeyWord(CourseSearchParam param);

    /**
     * 删除索引库中的信息.
     *
     * @param id
     * @return
     */
    ResponseResult delete(String id);

    SearchHit[] test(int page, int size, String keyword);

    /**
     * 课程分页查询具体实现.
     *
     * @param page
     * @param size
     * @param param
     * @return
     */
    QueryResponseResult list(int page, int size, CourseSearchParam param);

    /**
     * 按分类和难度等级进行搜索排序.
     *
     * @param page
     * @param size
     * @param param
     * @return
     */
    QueryResponseResult listByCategoryAndGrade(int page, int size, CourseSearchParam param);

    /**
     * 分页和高亮.
     * @param page
     * @param size
     * @param param
     * @return
     */
    QueryResponseResult listByPageSizeAndHighLight(int page, int size, CourseSearchParam param);

}
