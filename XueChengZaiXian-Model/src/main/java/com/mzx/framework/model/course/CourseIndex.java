package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:57
 */
@Data
@ToString
public class CourseIndex implements Serializable {
    private static final long serialVersionUID = -916357110051689587L;
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
    /**
     * 图片
     */
    private String pic;
    /**
     * 时间戳
     */
    private Date timestamp;
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private Date expires;
    /**
     * 课程计划
     */
    private String teachplan;


}

