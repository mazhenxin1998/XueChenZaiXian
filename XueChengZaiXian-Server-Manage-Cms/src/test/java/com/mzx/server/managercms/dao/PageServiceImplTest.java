package com.mzx.server.managercms.dao;

import com.mzx.framework.model.cms.CmsPage;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.service.IPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author ZhenXinMa
 * @date 2020/2/6 13:32
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class PageServiceImplTest {

    @Autowired
    private IPageService pageService;

    @Test
    public void testFindByPageName(){
        CmsPage byPageName = pageService.findByPageName("index.html");
        System.out.println(byPageName);

    }

}
