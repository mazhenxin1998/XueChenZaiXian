package com.mzx.servermanager.daotest;

import com.mzx.servermanager.dao.ICourseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/7 21:32
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseDaoTest {

    @Resource
    private ICourseDao courseDao;

    @Test
    public void t1(){

        Integer count = courseDao.getCount();
        System.out.println(count);

    }

}
