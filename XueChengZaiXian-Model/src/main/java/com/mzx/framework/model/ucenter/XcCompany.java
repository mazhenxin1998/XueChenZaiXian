package com.mzx.framework.model.ucenter;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
public class XcCompany implements Serializable {

    private static final long serialVersionUID = -916357110051689786L;
    private String id;
    private String name;
    private String logo;
    private String intro;
    private String email;
    private String mobile;
    private String linkname;
    private String identitypic;
    private String worktype;
    private String businesspic;
    private String status;

}
