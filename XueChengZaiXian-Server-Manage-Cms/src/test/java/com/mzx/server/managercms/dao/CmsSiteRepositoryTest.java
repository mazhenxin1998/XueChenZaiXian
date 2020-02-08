package com.mzx.server.managercms.dao;

import com.mzx.framework.model.cms.CmsSite;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.dao.CmsSiteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 16:03
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class CmsSiteRepositoryTest {

    @Autowired
    private CmsSiteRepository repository;


    @Test
    public void testGet(){

        List<CmsSite> all = repository.findAll();
        System.out.println(all.size());


    }

}
