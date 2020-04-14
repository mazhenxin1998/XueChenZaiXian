package com.mzx.framework.model.course;

import com.mzx.framework.model.course.ext.TeachPlanNode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 课程详情站点页面需要的模型数据.
 * @author ZhenXinMa
 * @date 2020/4/11 21:35
 */
@Data
@ToString
@NoArgsConstructor
public class CourseView implements Serializable {


    private CourseBase courseBase;
    private CoursePic coursePic;
    private TeachPlanNode teachPlanNode;
    private CourseMarket courseMarket;


}