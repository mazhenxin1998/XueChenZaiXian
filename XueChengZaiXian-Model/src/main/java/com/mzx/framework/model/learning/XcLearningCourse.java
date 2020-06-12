package com.mzx.framework.model.learning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 17:03
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class XcLearningCourse implements Serializable {

    private String id;
    private String course_id;
    private String user_id;
    private String valid;
    private Date start_time;
    private Date end_time;
    private String status;


}
