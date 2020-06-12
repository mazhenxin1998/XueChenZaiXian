package com.mzx.framework.model.search.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/6/2 19:55
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ElasticSearchMappingCourseMedia implements Serializable {

    private String courseid;
    private String teachplan_id;
    private String media_id;
    private String media_url;
    private String media_fileoriginalname;


}
