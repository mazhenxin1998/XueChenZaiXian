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
public class XcUser {

    private String id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String utype;
    private String birthday;
    private String userpic;
    private String sex;
    private String email;
    private String phone;
    private String status;
    private Date createTime;
    private Date updateTime;


}
