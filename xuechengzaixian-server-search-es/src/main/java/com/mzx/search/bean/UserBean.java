package com.mzx.search.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 20:29
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBean implements Serializable {


    private String name;
    private int age;


}
