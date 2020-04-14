package com.mzx.login.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/4/10 16:22
 */
@Data
@ToString
@Getter
@Setter
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;

}
