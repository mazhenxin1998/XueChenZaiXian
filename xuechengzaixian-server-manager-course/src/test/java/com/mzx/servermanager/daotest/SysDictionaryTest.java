package com.mzx.servermanager.daotest;

import com.mzx.framework.model.system.SysDictionary;
import com.mzx.servermanager.dao.ISysDictionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 19:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysDictionaryTest {

    @Resource
    private ISysDictionDao sysDictionDao;

    @Test
    public void t1(){

        List<SysDictionary> all = sysDictionDao.findAll();
        System.out.println(all);

    }

    @Test
    public void t2(){

        Optional<SysDictionary> o = sysDictionDao.findBydName("课程等级");
        if( o.isPresent() ){
            SysDictionary sysDictionary = o.get();
            System.out.println(1);
        }

    }


}
