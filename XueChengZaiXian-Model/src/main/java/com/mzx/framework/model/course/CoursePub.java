package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @pic 图片
 * @timestamp 时间戳
 * @teachplan 课程计划
 * @pubTime   课程发布时间
 * @author ZhenXinMa
 * @date 2020/3/22 22:07
 */
@Data
@ToString
public class CoursePub implements Serializable {
    private static final long serialVersionUID = -916357110051689487L;
    @Id
    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String pic;
    private Date timestamp;
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private String expires;
    private String teachplan;
    private String pubTime;
}
