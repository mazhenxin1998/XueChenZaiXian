package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:55
 */
@Data
@ToString
public class CourseBase implements Serializable {

    private static final long serialVersionUID = -916357110051689486L;

    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String status;
    /**
     *  驼峰自动转换
     */
    private String companyId;
    private String userId;

}
