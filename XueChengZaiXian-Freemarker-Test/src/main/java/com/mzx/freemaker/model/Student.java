package com.mzx.freemaker.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 21:54
 */
@Data
@ToString
@Accessors(chain = true)
public class Student {
    private String name;
    private int age;
    private Date birthday;
    private Float money;
}