package com.mzx.framework.model.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 21:54
 */
@Data
@ToString
@Document(collection = "fs.files")
public class CmsFile {

    private String id;
    private String filename;
    private Long length;

}
