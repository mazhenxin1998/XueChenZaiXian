package com.mzx.servermanager.daotest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.ext.CourseInfo;
import com.mzx.framework.model.course.requesed.CourseListRequest;
import com.mzx.servermanager.dao.ICourseBaseDao;
import com.mzx.servermanager.dao.ICourseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/4/2 9:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageHelperTest {

    @Resource
    private ICourseDao courseDao;

    @Resource
    private ICourseBaseDao courseBaseDao;

    @Test
    public void t1(){
        PageHelper.startPage(1,10);
        CourseListRequest request = new CourseListRequest();
        List<CourseInfo> info = courseDao.getInfo(request);
        // 对其进行封装
        PageInfo<CourseInfo> page = new PageInfo<CourseInfo>(info);
        System.out.println(1);
    }

    @Test
    public void t2(){
        PageHelper.startPage(1,10);
        Page<CourseBase> page = courseDao.get();
        System.out.println(1);
        System.out.println(2);
    }

    @Test
    public void t3(){

//        PageHelper.startPage(1,10);
//        Page<CourseInfo> pageInfo = courseDao.getPageInfo();
//        List<CourseInfo> list = pageInfo.getResult();
//        System.out.println(1);

    }

    @Test
    public void t4(){
        CourseBase byId = courseBaseDao.findById("297e7c7c62b888f00162b8a7dec20000");
        System.out.println("1");
    }

}
