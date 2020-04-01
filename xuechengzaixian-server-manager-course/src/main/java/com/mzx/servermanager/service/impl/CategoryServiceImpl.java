package com.mzx.servermanager.service.impl;

import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.course.Category;
import com.mzx.framework.model.course.ext.CategoryNode;
import com.mzx.framework.model.course.response.CourseCode;
import com.mzx.servermanager.dao.ICategoryDao;
import com.mzx.servermanager.service.ICategoryService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 18:37
 */
@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private ICategoryDao categoryDao;

    @Override
    public CategoryNode get() {
        return categoryDao.get();
    }

}
