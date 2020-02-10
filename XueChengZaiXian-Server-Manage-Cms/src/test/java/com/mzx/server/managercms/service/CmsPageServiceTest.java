package com.mzx.server.managercms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.service.IPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 17:13
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class CmsPageServiceTest {

    @Autowired
    private IPageService service;

    @Autowired
    private CmsPageRepository repository;

    @Test
    public void addPage(){

        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("门户主站")
                .setPagePhysicalPath("ssssss")
                .setPageStatus("0")
                .setPageTemplate("0")
                .setPageType("0")
                .setSiteId("0");
        CmsPage save = repository.save(cmsPage);
        System.out.println("1");

    }

    @Test
    public void find(){
        Optional<CmsPage> o = repository.findByPageNameAndSiteIdAndPageWebPath("index.html", "5a751fab6abb5044e0d19ea1", "/index.html");
        Optional<CmsPage> o2 = repository.findByPageNameAndSiteIdAndPageWebPath("测试页面01", "s01", "s");
        if(ObjectUtils.isEmpty(o2)){
            System.out.println("执行");
        }
        System.out.println(2);
    }
}
