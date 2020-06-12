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
public class XcPermission {


    private String id;

    private String role_id;

    private String menu_id;

    private Date create_time;


}
