package com.mzx.servermanager.daotest;


import com.mzx.framework.model.course.Category;
import com.mzx.framework.model.course.ext.TeachPlanDaoReceive;
import com.mzx.framework.model.course.ext.TeachPlanNode;
import com.mzx.servermanager.dao.ICategoryDao;
import com.mzx.servermanager.dao.ITeachPlanDao;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryDaoTest {

    @Resource
    private ICategoryDao categoryDao;

    @Resource
    private ITeachPlanDao teachPlanDao;

    @Test
    public void t1(){

        List<Category> all = categoryDao.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
    }

    @Test
    public void t2(){

        List<TeachPlanDaoReceive> node = teachPlanDao.getNodeByID("4028e581617f945f01617f9dabc40000");
        System.out.println(node.size());
        System.out.println(node.get(1));
    }

    @Test
    public void t3(){
        TeachPlanNode node = teachPlanDao.getNode("4028e581617f945f01617f9dabc40000");
        System.out.println(node);
    }


}
