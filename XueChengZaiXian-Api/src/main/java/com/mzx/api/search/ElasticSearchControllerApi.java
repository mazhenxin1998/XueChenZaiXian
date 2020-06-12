package com.mzx.api.search;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.course.TeachPlanMediaPub;
import com.mzx.framework.model.search.CourseSearchParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/4/25 17:02
 */
@Api(value = "ElasticSearch接口")
public interface ElasticSearchControllerApi {

    /**
     * 向ES中增加课程相关信息.
     * <p>
     * 教师在发布了课程之后,应该首先向ES中增加该课程的基本信息.
     *
     * @param coursePub
     * @return
     */
    @ApiOperation(value = "向ES中增加索引信息")
    ResponseResult addIndex(CoursePub coursePub);

    /**
     * 客户端发送Search请求到ES服务中，ES返回高亮的搜索结果.
     *
     * @return
     */
    @ApiOperation(value = "ES高亮搜索服务")
    QueryResponseResult search(CourseSearchParam param);

    /**
     * 当mysql中的数据进行更新时,也同时更新ES索引库
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除ES索引库中的信息.")
    ResponseResult delete(String id);

    /**
     * 课程分页查询接口.
     *
     * @param page  要查询得页面 和MySQL类似.
     * @param size  每页显示课程数量的大小.
     * @param param 查询参数.
     * @return
     */
    @ApiOperation(value = "分页高亮查询")
    QueryResponseResult list(int page, int size, CourseSearchParam param);


    /**
     * 根据课程ID返回JSON格式的课程id和课程的Value.
     * Key为课程的ID,Value为课程的值.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据 ID查询课程信息")
    Map<String, CoursePub> getAll(String id);

    /**
     * 根据课程计划ID查询出该课程计划所对应的媒资信息.
     *
     * @param teachPlanID
     * @return
     */
    @ApiOperation(value = "根据课程计划ID查询出该计划所对应的媒资信息")
    TeachPlanMediaPub getMedia(String teachPlanID);


}
