package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsPageControllerApi;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.Student;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 21:34
 */
@RestController
@RequestMapping(value = "/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @GetMapping(value = "/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int number,
                                        @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        List<CmsPage> all = cmsPageRepository.findAll();
        if(all!=null){
            System.out.println(all.size());
        }

        List<Student> all1 = studentRepository.findAll();
        if( all1 != null){
            System.out.println(all1);
        }


        // 测试分页查询
        Pageable pageable = PageRequest.of(number,size);
        Page<CmsPage> all2 = cmsPageRepository.findAll(pageable);
        for (CmsPage cmsPage : all2) {
            System.out.println(cmsPage);
        }

        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        List<CmsPage> list = new ArrayList<>();
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        list.add(cmsPage);
        queryResult.setList(list);
        queryResult.setTotal(1L);

        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
}
