package com.mzx.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ZhenXinMa
 * @date 2020/2/15 22:21
 */
@Data
@ToString
@Document(collection = "student")
public class Student {

    private String id;
    private String name;
    private String age;

}
