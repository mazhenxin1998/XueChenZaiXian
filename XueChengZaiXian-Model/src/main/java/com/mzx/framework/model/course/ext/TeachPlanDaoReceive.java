package com.mzx.framework.model.course.ext;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 11:37
 */
@Data
@ToString
public class TeachPlanDaoReceive implements Serializable {

    private static final long serialVersionUID = -906357110051689484L;

    private String one_id;
    private String one_name;
    private String two_id;
    private String two_name;
    private String three_id;
    private String three_name;


}
