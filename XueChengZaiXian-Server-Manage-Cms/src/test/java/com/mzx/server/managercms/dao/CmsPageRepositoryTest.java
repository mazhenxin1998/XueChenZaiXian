package com.mzx.server.managercms.dao;

import com.mzx.common.model.response.QueryResponseResult;

import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.server.managecms.ServerManageCmsApp;

import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.service.IPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/7 22:54
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository repository;

    @Autowired
    private IPageService service;

    @Test
    public void test01() {
        List<CmsPage> lists = repository.findAll();

        // 使用条件匹配器进行 精确查询
        ExampleMatcher matcher = ExampleMatcher.matching();

        // 设置匹配条件
        matcher = matcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //  ExampleMatcher.GenericPropertyMatchers.contains() 匹配包含的意思
        //  ExampleMatcher.GenericPropertyMatchers.startWith()从头开始匹配

        //  创建条件模板
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("10101.html")
                .setSiteId("5a751fab6abb5044e0d19ea1").setTemplateId("5a925be7b00ffc4b3c1578b5")
                .setPageAliase("课程详情页面");

        // 创建条件实例
        Example<CmsPage> example = Example.of(cmsPage,matcher);

        // 查询
        Pageable pageable = new PageRequest(0,10);

        Page<CmsPage> s = repository.findAll(example,pageable);
        List<CmsPage> list = s.getContent();
        for (CmsPage page : list) {
            System.out.println(list);
        }
        System.out.println(1);

    }
    
    @Test
    public void findBySiteId(){

        Optional<List<CmsPage>> optional = repository.findBySiteId("5a751fab6abb5044e0d19ea1");
        if(optional.isPresent()){
            List<CmsPage> list = optional.get();
            System.out.println("2");
        }

    }

    @Test
    public void findByTemplateID(){
        Optional<List<CmsPage>> o = repository.findByTemplateId("5a962b52b00ffc514038faf7");
        if( o.isPresent() ){
            List<CmsPage> list = o.get();
            for (CmsPage cmsPage : list) {
                System.out.println(cmsPage.getTemplateId());

            }
        }
    }

    @Test
    public void findByPageAlias(){
        Optional<List<CmsPage>> o = repository.findByPageAliase("java基础3");
        if( o.isPresent() ){
            List<CmsPage> list = o.get();
            for (CmsPage cmsPage : list) {
                System.out.println(cmsPage);
            }
        }
    }

    @Test
    public void findBySiteIdAndPageAliase(){
        QueryPageRequest request = new QueryPageRequest();
        request.setPageAliase("课程详情页面");
        request.setSiteId("5a751fab6abb5044e0d19ea1");
        QueryResponseResult queryResponseResult = service.get(request);
        System.out.println(1);

    }

    @Test
    public void addCmsPage(){

        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageAliase("测试111").setPageCreateTime(LocalDate.now().toString()).setPageName("测试111的别名")
                .setPagePhysicalPath("物理地址")
                .setPageStatus("sss")
                .setPageType("测试")
                .setPageWebPath("/fadf")
                .setTemplateId("11")
                .setSiteId("5b30b052f58b4411fc6cb1cf");
        repository.save(cmsPage);

    }

    @Test
    public void updatePage(){

        Optional<CmsPage> o = repository.findById("5e3ead1eab234953acd13113");
        if( o.isPresent() ){
            CmsPage cmsPage = o.get();
            cmsPage.setPageName("我是修改之后的名字");
        }

    }

    /**
     *   "_id" : ObjectId("5e3fd599ab234952a066c2cc"),
     *   "siteId" : "5b30b052f58b4411fc6cb1cf",
     *   "pageName" : "www",
     *   "pageAliase" : "www",
     *   "pageWebPath" : "www",
     *   "pageParameter" : "",
     *   "pagePhysicalPath" : "www",
     *   "pageType" : "0",
     *   "pageCreateTime" : "2020-02-09T09:48:41.539Z",
     *   "templateId" : "5a962b52b00ffc514038faf7",
     *   "_class" : "com.mzx.framework.model.cms.CmsPage"
     * }
     */
    @Test
    public void update (){



    }

}
