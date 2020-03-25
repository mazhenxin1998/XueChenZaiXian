package com.mzx.framework.model.course;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 *
 * @author ZhenXinMa
 * @date 2020/3/22 22:10
 */
@Data
@ToString
public class TeachPlanMedia implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    @Id
    private String teachplanId;
    private String mediaId;
    private String mediaFileOriginalName;
    private String mediaUrl;
    private String courseId;

}