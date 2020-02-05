package com.mzx.framework.model.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 22:33
 */
@Data
@ToString
@Document(collection = "student")
public class Student {

    private String id;

    private String name;

}
