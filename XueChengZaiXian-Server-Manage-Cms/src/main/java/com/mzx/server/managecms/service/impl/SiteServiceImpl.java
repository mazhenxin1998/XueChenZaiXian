package com.mzx.server.managecms.service.impl;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.cms.CmsSite;
import com.mzx.server.managecms.dao.CmsSiteRepository;
import com.mzx.server.managecms.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 15:57
 */
@Service
public class SiteServiceImpl implements ISiteService {

    @Autowired
    private CmsSiteRepository repository;

    @Override
    public QueryResponseResult get() {

        List<CmsSite> all = repository.findAll();
        QueryResult<CmsSite> queryResult = new QueryResult<CmsSite>();
        queryResult.setList(all);
        queryResult.setTotal((long) all.size());

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public CmsSite getByID(String siteID) {

        Optional<CmsSite> optional = repository.findById(siteID);
        if (optional.isPresent()) {

            return optional.get();
        }

        return null;
    }

}
