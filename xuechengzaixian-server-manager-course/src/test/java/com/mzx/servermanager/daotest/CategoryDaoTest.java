package com.mzx.servermanager.daotest;


import com.mzx.framework.model.course.Category;
import com.mzx.framework.model.course.TeachPlan;
import com.mzx.framework.model.course.ext.CategoryNode;
import com.mzx.framework.model.course.ext.TeachPlanNode;
import com.mzx.servermanager.dao.ICategoryDao;
import com.mzx.servermanager.dao.ITeachPlanDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryDaoTest {


    @Resource
    private ITeachPlanDao teachPlanDao;


    @Resource
    private ICategoryDao categoryDao;


    @Test
    public void t3(){
        TeachPlanNode node = teachPlanDao.getNode("4028e581617f945f01617f9dabc40000");
        System.out.println(node);
    }

    @Test
    public void t4(){

        TeachPlan t1 = new TeachPlan();
        String s = UUID.randomUUID().toString();
        t1.setId(s);
        t1.setCourseid("1231131312313");
        t1.setPname("测试增加时候用的");
        t1.setPtype("0");
        t1.setStatus("0");
        t1.setParentid("0");
        t1.setGrade("1");
        teachPlanDao.add(t1);
        TeachPlan teachPlan = teachPlanDao.getByID(s);
        System.out.println(teachPlan);


    }

    @Test
    public void t5(){

        CategoryNode node = categoryDao.get();
        System.out.println(1);


    }


}
