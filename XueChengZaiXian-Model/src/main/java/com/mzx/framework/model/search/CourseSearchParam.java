package com.mzx.framework.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/4/28 8:36
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchParam implements Serializable {

    /**
     * 要查询的关键字.
     */
    String keyword;
    /**
     * 一级分类
     */
    String mt;
    /**
     * 二级分类
     */
    String st;
    /**
     * 难度等级
     */
    String grade;
    /**
     * 价格区间(排序)
     */
    Double price_min;
    Double price_max;
    /**
     * 排序字段
     */
    String sort;
    /**
     * 过虑字段
     */
    String filter;
    /**
     * 分页参数
     */
    int page;
    int size;



}

