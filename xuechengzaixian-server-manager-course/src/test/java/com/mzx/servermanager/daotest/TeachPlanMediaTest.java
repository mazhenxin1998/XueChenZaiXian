package com.mzx.servermanager.daotest;

import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.servermanager.ApplicationServerManagerCourseApp;
import com.mzx.servermanager.dao.ITeachPlanMediaDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhenXinMa
 * @date 2020/5/26 22:06
 */
@SpringBootTest(classes = {ApplicationServerManagerCourseApp.class})
@RunWith(SpringRunner.class)
public class TeachPlanMediaTest {


    @Autowired
    private ITeachPlanMediaDao iTeachPlanMediaDao;

    @Test
    public void t1(){

        TeachPlanMedia media = iTeachPlanMediaDao.findById("297e02f7639af61a01639afd3a7b0000");
        System.out.println(media);
        System.out.println(1);

    }

    @Test
    public void t2(){



    }




}
