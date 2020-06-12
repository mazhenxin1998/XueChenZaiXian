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
public class XcRole {

    private String id;

    private String roleName;

    private String role_code;
    private String description;
    private String status;

    private Date create_time;

    private Date updateTime;


}
