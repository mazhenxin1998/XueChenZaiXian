package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:05
 */
@Data
@ToString
public class CoursePic implements Serializable {

    private static final long serialVersionUID = -916357110051689486L;

    @Id
    private String courseid;
    private String pic;

}
