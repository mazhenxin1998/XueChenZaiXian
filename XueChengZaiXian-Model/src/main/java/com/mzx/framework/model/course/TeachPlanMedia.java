package com.mzx.framework.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 存放在MySQL中.
 *
 * @author ZhenXinMa
 * @date 2020/3/22 22:10
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeachPlanMedia implements Serializable {

    private static final long serialVersionUID = -916357110051689485L;

    @Id
    private String teachplanId;
    private String mediaId;
    private String mediaFileoriginalname;
    private String mediaUrl;
    private String courseid;

}