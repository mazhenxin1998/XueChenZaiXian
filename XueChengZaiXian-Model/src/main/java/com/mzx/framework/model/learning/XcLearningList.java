package com.mzx.framework.model.learning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 17:07
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class XcLearningList implements Serializable {

    private String id = "";
    private String course_id = "";
    private String user_id = "";
    private Date start_time = null;
    private Date end_time = null;



}
