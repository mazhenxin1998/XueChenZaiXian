package com.mzx.servermanager.controller;

import com.mzx.servermanager.dao.ICategoryDao;
import com.mzx.servermanager.dao.ITeachPlanDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:32
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private ICategoryDao categoryDao;

    @Resource
    private ITeachPlanDao teachPlanDao;

    @GetMapping(value = "/t1")
    public Object t1(){
        return categoryDao.getAll();
    }

    @GetMapping(value = "/t2")
    public String t2(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        String parent = new File(application.getRealPath(request.getRequestURI())).getParent();
        String parent0 = new File(application.getRealPath(request.getServletPath())).getParent();
        System.out.println(parent);
        return parent+"<br>"+parent0 ;
    }

    @GetMapping(value = "/t3/{id}")
    public Object t3(@PathVariable String id){
        return  teachPlanDao.getNode(id);
    }



}
