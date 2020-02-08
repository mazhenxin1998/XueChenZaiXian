package com.mzx.server.managercms.dao;

import com.mzx.framework.model.cms.CmsTemplate;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.dao.CmsTemplateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:14
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class CmsTemplateRepositoryTest {

    @Autowired
    private CmsTemplateRepository repository;

    @Test
    public void getAll(){
        List<CmsTemplate> all = repository.findAll();
        System.out.println("2");

    }

}
