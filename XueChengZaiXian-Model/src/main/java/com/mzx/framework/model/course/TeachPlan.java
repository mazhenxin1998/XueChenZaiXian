package com.mzx.framework.model.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:09
 */
@Data
@ToString
@Accessors(chain = true)
@ApiModel
public class TeachPlan implements Serializable {

    private static final long serialVersionUID = -916357110051689485L;

    @ApiModelProperty(value = "课程计划ID")
    private String id;

    @ApiModelProperty(value = "课程计划名字1")
    private String pname;

    @ApiModelProperty(value = "该课程计划是否有父节点1")
    private String parentid;

    @ApiModelProperty(value = "成绩")
    private String grade;

    @ApiModelProperty(value = "当前课程计划的类型1")
    private String ptype;

    @ApiModelProperty(value = "课程计划的描述1")
    private String description;

    @ApiModelProperty(value = "课程ID")
    private String courseid;

    @ApiModelProperty(value = "当前课程计划的状态1")
    private String status;

    @ApiModelProperty(value = "排序字段1")
    private String orderby;

    @ApiModelProperty(value = "XXX1")
    private Double timelength;

    @ApiModelProperty(value = "XXX")
    private String trylearn;

}
