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

    private String courseid;
    /**
     * pic对应的是url
     */
    private String pic;
    private String fileName;
    private String status;

}
