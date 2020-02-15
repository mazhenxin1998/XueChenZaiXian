package com.mzx.testtemplate.dao;

import com.mzx.bean.Student;
import com.mzx.freemaker.FreemakerApp;
import com.mzx.freemaker.dao.ITestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/15 22:23
 */
@SpringBootTest(classes = {FreemakerApp.class})
@RunWith(SpringRunner.class)
public class StudentTest {

    @Autowired
    private ITestRepository repository;
    @Test
    public void t1(){

        List<Student> all = repository.findAll();
        System.out.println(1);


    }



}
