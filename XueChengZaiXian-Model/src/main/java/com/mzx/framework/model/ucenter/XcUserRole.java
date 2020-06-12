package com.mzx.framework.model.ucenter;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by admin on 2018/3/19.
 */
@Data
@ToString
public class XcUserRole {


    private String id;
    private String userId;
    private String roleId;
    private String creator;
    private Date createTime;

}
