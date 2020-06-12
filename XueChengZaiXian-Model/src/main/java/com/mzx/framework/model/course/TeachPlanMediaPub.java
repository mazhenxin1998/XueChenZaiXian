package com.mzx.framework.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/6/1 21:12
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TeachPlanMediaPub implements Serializable {

    private String teachplan_id;
    private String media_id;
    private String media_fileoriginalname;
    private String media_url;
    private String courseid;
    private String timestamp;


    public String getTeachplan_id() {
        return teachplan_id;
    }

    public String getMedia_id() {
        return media_id;
    }

    public String getMedia_fileoriginalname() {
        return media_fileoriginalname;
    }

    public String getMedia_url() {
        return media_url;
    }

    public String getCourseid() {
        return courseid;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
