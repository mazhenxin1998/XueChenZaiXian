package com.mzx.server.managecms.controller;

import com.mzx.api.cms.CmsPageControllerApi;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.Student;
import com.mzx.framework.model.cms.requesed.AddPageRequest;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.framework.model.course.response.CmsPostPageResult;
import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.dao.StudentRepository;
import com.mzx.server.managecms.service.IPagePreviewService;
import com.mzx.server.managecms.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     * 底层是JDK动态代理
     */
    @Autowired
    private IPageService pageService;

    @Resource
    private CmsPageRepository repository;

    /**
     * 页面发布
     *
     * @param pageID
     * @return
     */
    @Override
    @PostMapping(value = "/post/publish/{pageID}")
    public ResponseResult publishCmsPage(@PathVariable(value = "pageID") String pageID) {
        return pageService.publishPage(pageID);
    }

    @Override
    @GetMapping(value = "/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,
                                        @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        return pageService.findList(page, size, queryPageRequest);
    }

    @Override
    @GetMapping(value = "/get")
    public QueryResponseResult get(QueryPageRequest queryPageRequest) {
        return pageService.get(queryPageRequest);
    }

    @Override
    @PostMapping(value = "/add")
    public QueryResponseResult add(@RequestBody CmsPage request) {
        return pageService.add(request);
    }

    @Override
    @GetMapping(value = "/delete/{pageID}")
    public QueryResponseResult delete(@PathVariable(value = "pageID") String pageID) {
        System.out.println(pageID);
        System.out.println("删除ID" + pageID + "方法执行了");
        QueryResponseResult result = pageService.delete(pageID);
        return result;
    }

    @Override
    @PostMapping(value = "/update/{id}")
    public QueryResponseResult update(@RequestBody CmsPage request, @PathVariable(value = "id") String id) {
        return pageService.update(request, id);
    }

    @Override
    @GetMapping(value = "/get/{name}")
    public CmsPage getByName(@PathVariable(value = "name") String name) {

        Optional<CmsPage> o = repository.findByPageName(name);

        return o.get();
    }

    @Override
    @PostMapping(value = "/post/add/page")
    public CmsPage addCmsPage(@RequestBody CmsPage page) {
        /*如果发现已经存在了那么就删除已经存在的,然后再添加.*/
        return pageService.addPage(page);
    }

    @Override
    @PostMapping(value = "/quick/postPage")
    public CmsPostPageResult postPageQuick(@RequestBody CmsPage cmsPage) {
        /*   /cms/page/quick/postPage*/
        return pageService.postPageQuick(cmsPage);
    }


}
