package com.mzx.server.managecms.service.impl;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.cms.CmsPage;
import com.mzx.framework.model.cms.requesed.AddPageRequest;
import com.mzx.framework.model.cms.requesed.QueryPageRequest;
import com.mzx.server.managecms.dao.CmsPageRepository;
import com.mzx.server.managecms.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


/**
 * @author ZhenXinMa
 * @date 2020/2/6 13:11
 */
@Service
public class PageServiceImpl implements IPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Override
    public QueryResponseResult get( QueryPageRequest request) {
        System.out.println(request);
        QueryResponseResult result = null;
        QueryResult<CmsPage> queryResult = null;

        // 根据站点ID精确查询
        if((request.getSiteId() != null || request.getSiteId() != "")&&(request.getPageAliase() ==null || request.getPageAliase()=="")){
            System.out.println(1111);
            Optional<List<CmsPage>> o = cmsPageRepository.findBySiteId(request.getSiteId());
            if( o.isPresent() ){

                List<CmsPage> list = o.get();
                queryResult = new QueryResult<CmsPage>();
                queryResult.setList(list);
                queryResult.setTotal((long) list.size());

                result = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
            }

        }else if( (request.getPageAliase() != null || request.getPageAliase() != "")&&(request.getSiteId() == null || request.getSiteId() == "")){
            // 根据别名查询
            System.out.println(2222);
            Optional<List<CmsPage>> o = cmsPageRepository.findByPageAliase(request.getPageAliase());
            if( o.isPresent() ){

                List<CmsPage> list = o.get();
                queryResult = new QueryResult<CmsPage>();
                queryResult.setTotal((long) list.size());
                queryResult.setList(list);

                result = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
            }

        } else if ( request.getTemplateId() != null ){
            // 根据模板ID查询
            Optional<List<CmsPage>> o = cmsPageRepository.findByTemplateId(request.getTemplateId());
            System.out.println(3333);
            if( o.isPresent() ){

                List<CmsPage> cmsPages = o.get();
                queryResult = new QueryResult<>();
                queryResult.setList(cmsPages);
                queryResult.setTotal((long) cmsPages.size());

                result = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
            }else{
                result = new QueryResponseResult(CommonCode.FAIL,null);
            }


        } else if (request.getSiteId()!= null && request.getPageAliase()!=null){
            // 根据站点ID和站点别名进行查询

            Optional<List<CmsPage>> o = cmsPageRepository.findBySiteIdAndPageAliase(request.getSiteId(), request.getPageAliase());
            if( o.isPresent() ){
                System.out.println(4444);
                List<CmsPage> list = o.get();
                queryResult = new QueryResult<>();
                queryResult.setList(list);
                queryResult.setTotal((long) list.size());
                result = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
            }else {
                System.out.println(5555);
                result = new QueryResponseResult(CommonCode.FAIL,null);
            }

        } else {
            // 请求参数错误 返回错误结果
            queryResult = new QueryResult<CmsPage>();
            queryResult.setList(null);
            queryResult.setTotal(-1L);
            result = new QueryResponseResult(CommonCode.FAIL,queryResult);
        }


        return result;
    }

    @Override
    public QueryResponseResult add(CmsPage cmsPage) {
        cmsPageRepository.save(cmsPage);
        return new  QueryResponseResult(CommonCode.SUCCESS,null);
    }

    @Override
    public QueryResponseResult delete(QueryPageRequest request) {
        return null;
    }

    @Override
    public QueryResponseResult update(CmsPage request) {
        return null;
    }


    @Override
    public CmsPage findByPageName(String name) {
        if(!StringUtils.isEmpty(name)){
            //不空
            Optional<CmsPage> o = cmsPageRepository.findByPageName(name);
            if( o.isPresent() ){
                //如果 o 存在
                CmsPage cmsPage = o.get();
                return cmsPage;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    /**
     *
     * @param page                第几页
     * @param size                每个页面上的数量
     * @param queryPageRequest    查询条件的接口
     * @return                    查询结果
     */
    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {

        if(queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }

        if( page <= 0 ){
            page = 1;
        }
        // 为了适应MongoDB的接口 将页码-1
        page = page -1;

        if( size <=0 ){
            size = 20;
        }

        //  分页查询
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> lists = cmsPageRepository.findAll(pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        queryResult.setList(lists.getContent());
        queryResult.setTotal((long) lists.getSize());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);

        return queryResponseResult;
    }
}
