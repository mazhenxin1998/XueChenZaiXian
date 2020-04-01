package com.mzx.servermanager.controller;

import com.mzx.api.course.ICategoryControllerApi;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.course.ext.CategoryNode;
import com.mzx.servermanager.service.ICategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 17:47
 */
@RestController
@RequestMapping(value = "/course/me")
public class CategoryController implements ICategoryControllerApi {

    @Resource
    private ICategoryService categoryService;

    @Override
    @GetMapping(value = "/category/get")
    public CategoryNode get() {
        return categoryService.get();
    }
}
