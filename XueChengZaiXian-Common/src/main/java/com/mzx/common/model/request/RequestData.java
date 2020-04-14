package com.mzx.common.model.request;

import lombok.Data;
import lombok.ToString;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 15:15
 */
@Data
@ToString
public class RequestData implements Serializable {

    private static final long serialVersionUID = -916357110051689485L;

    private InputStream inputStream;
    private String fileType;
    private String fileName;

    private String url;
    private String courseID;


}
