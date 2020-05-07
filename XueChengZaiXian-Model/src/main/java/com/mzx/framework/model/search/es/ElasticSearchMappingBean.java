package com.mzx.framework.model.search.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/4/28 9:05
 */
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ElasticSearchMappingBean implements Serializable {

    private String description;
    private String name;
    private String pic;
    private float price;
    private String studymodel;
    private Date timestamp;


}
