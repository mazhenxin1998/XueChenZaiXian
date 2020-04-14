package com.mzx.servermanager.controller;

import com.mzx.common.model.request.RequestData;
import com.mzx.servermanager.dao.ICategoryDao;
import com.mzx.servermanager.dao.ITeachPlanDao;
import com.mzx.servermanager.feign.IUcloudControllerFeign;
import freemarker.template.utility.NormalizeNewlines;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

    @Resource
    private IUcloudControllerFeign ucloudControllerFeign;

    @GetMapping(value = "/t1")
    public Object t1() {
        return categoryDao.getAll();
    }

    @PostMapping(value = "/t3")
    public String t2(Model model) {
        try {

            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "发生异常了";
        }
    }

    @GetMapping(value = "/t2")
    public String t2(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        /*以当前路径 查找父类路径*/
        String parent = new File(application.getRealPath(request.getRequestURI())).getParent();
        String parent0 = new File(application.getRealPath(request.getServletPath())).getParent();
        String contentType = request.getContextPath();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String servletPath = request.getServletPath();
        System.out.println(parent);
        return "application.getRealPath(request.getRequestURI())).getParent()   "+parent + "<br>" +
                "application.getRealPath(request.getServletPath())).getParent()   " +parent0+"<br>"+
                "request.getContentType    "+contentType+"<br>"+
                "request.getMethod     "+method+"<br>"+
                "request.getRequestURI      "+requestURI+"<br>"+
                "request.getRequestURL      "+requestURL+"<br>"+
                "request.getServletPath     "+servletPath;

    }

    @GetMapping(value = "/t3/{id}")
    public Object t3(@PathVariable String id) {
        return teachPlanDao.getNode(id);
    }


}
