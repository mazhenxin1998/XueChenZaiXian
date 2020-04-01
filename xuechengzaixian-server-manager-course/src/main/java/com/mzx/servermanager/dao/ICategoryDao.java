package com.mzx.servermanager.dao;

import com.mzx.framework.model.course.Category;
import com.mzx.framework.model.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:18
 */
@Mapper
public interface ICategoryDao {

    List<Category> getAll();

    CategoryNode get();


}
