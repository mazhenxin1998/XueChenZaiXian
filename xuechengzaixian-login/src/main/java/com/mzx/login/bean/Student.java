package com.mzx.login.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 16:58
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    /**
     * String类型可以使用 @NotBlank,@NotEmpty来对参数进行校验.
     */

    private String id;


    private String name;


    private int age;

}
