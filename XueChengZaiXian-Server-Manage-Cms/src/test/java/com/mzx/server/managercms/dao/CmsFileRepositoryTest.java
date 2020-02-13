package com.mzx.server.managercms.dao;

import com.mzx.framework.model.cms.CmsFile;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.dao.CmsFileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 22:15
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class CmsFileRepositoryTest {

    @Autowired
    CmsFileRepository repository;

    @Test
    public void t1(){
        List<CmsFile> all = repository.findAll();
        System.out.println(1);

    }

    @Test
    public void t2(){

        //  5e4409ddab23495df8493c2c
        Optional<CmsFile> o = repository.findById("5e4409ddab23495df8493c2c");
        if( o.isPresent() ){
            CmsFile file = o.get();
            System.out.println(1);
        }

    }

    @Test
    public void t3(){
        repository.deleteById("5e4409ddab23495df8493c2c");
    }

    @Test
    public void t4(){
        List<CmsFile> files = repository.findByFilename("user7.png");
        System.out.println(1);
    }

}
