package com.mzx.server.managecms.service.impl;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.cms.CmsTemplate;
import com.mzx.server.managecms.dao.CmsTemplateRepository;
import com.mzx.server.managecms.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:10
 */
@Service
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private CmsTemplateRepository repository;

    @Override
    public QueryResponseResult get() {
        List<CmsTemplate> all = repository.findAll();
        QueryResult<CmsTemplate> template = new QueryResult<CmsTemplate>();
        template.setList(all);
        template.setTotal((long) all.size());
        return new QueryResponseResult(CommonCode.SUCCESS,template);
    }
}
