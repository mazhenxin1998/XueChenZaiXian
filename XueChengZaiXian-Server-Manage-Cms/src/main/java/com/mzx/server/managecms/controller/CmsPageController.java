package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsPageControllerApi;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.Student;
import com.mzx.framework.model.cms.requesed.AddPageRequest;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.dao.StudentRepository;
import com.mzx.server.managecms.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/5 21:34
 */
@RestController
@RequestMapping(value = "/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    /**
     *   底层是JDK动态代理
     */
    @Autowired
    private IPageService pageService;

    @Override
    @GetMapping(value = "/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,
                                        @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        return pageService.findList(page,size,queryPageRequest);
    }

    @Override
    @GetMapping(value = "/get")
    public QueryResponseResult get( QueryPageRequest queryPageRequest) {
        return pageService.get(queryPageRequest);
    }

    @Override
    @PostMapping(value = "/add")
    public QueryResponseResult add(CmsPage request) {
        return pageService.add(request);
    }

    @Override
    public QueryResponseResult delete(QueryPageRequest request) {
        return null;
    }

    @Override
    public QueryResponseResult update(CmsPage request) {
        return null;
    }
}
