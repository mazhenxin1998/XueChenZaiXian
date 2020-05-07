package com.mzx.framework.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
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
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CoursePub implements Serializable {

    private static final long serialVersionUID = -916357110051689487L;
    /**
     * id应该是课程ID. 1
     */
    private String id;
    /**
     * 课程名字. 1
     */
    private String name;
    /**
     * 适应人群. 1
     */
    private String users;
    /**
     * 分类. 1
     */
    private String mt;
    /**
     * 分类.1
     */
    private String st;
    /**
     * 难度等级. 1
     */
    private String grade;
    /**
     * 学习模式.1
     */
    private String studymodel;
    /**
     * 教学模式.1
     */
    private String teachmode;
    /**
     * 课程描述. 1
     */
    private String description;
    /**
     * 课程图片地址. 1
     */
    private String pic;
    /**
     * 时间戳.1
     */
    private Date timestamp;
    // 1
    private String charge;
    // 不知道具体的意思 暂时没有设置
    private String valid;
    // 1
    private String qq;
    /**
     * 价格. 1
     */
    private Double price;
    /**
     * 更改前的价格. 1
     */
    private Double price_old;
    // 1
    private String expires;
    /**
     * 课程计划： 要求是以JSON串的形式存入数据库中.1
     */
    private String teachplan;
    /**
     * 发布时间. 1
     */
    private String pubTime;
}
