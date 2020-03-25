package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:04
 */
@Data
@ToString
public class CourseOff implements Serializable {
    private static final long serialVersionUID = -916357110051689488L;
    @Id
    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String description;
    /**
     * pic ： 图片.
     * timestamp:  时间戳.
     * teachplan:  课程计划.
     */
    private String pic;
    private Date timestamp;
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private Date expires;
    private String teachplan;


}
