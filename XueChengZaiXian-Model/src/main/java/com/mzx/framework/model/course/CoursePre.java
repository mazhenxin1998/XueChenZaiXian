package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:06
 */
@Data
@ToString
public class CoursePre implements Serializable {
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
    private String pic;//图片
    private Date timestamp;//时间戳
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private Date expires;
    private String teachplan;//课程计划


}

