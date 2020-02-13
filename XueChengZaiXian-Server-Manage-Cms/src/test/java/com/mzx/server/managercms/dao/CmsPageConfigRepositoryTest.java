package com.mzx.server.managercms.dao;

import com.mzx.framework.model.cms.CmsConfig;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.dao.CmsPageConfigRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:40
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class CmsPageConfigRepositoryTest {

    @Autowired
    private CmsPageConfigRepository repository;

    @Test
    public void findById(){

        Optional<CmsConfig> o = repository.findById("5a791725dd573c3574ee333f");
        if( o.isPresent() ){
            CmsConfig cmsConfig = o.get();
            System.out.println(repository.getClass());
            System.out.println(1);
        }

    }

    @Test
    public void get(){

    }

}
