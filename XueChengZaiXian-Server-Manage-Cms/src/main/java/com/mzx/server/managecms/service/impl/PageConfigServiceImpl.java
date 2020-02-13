package com.mzx.server.managecms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.*;
import com.mzx.framework.model.cms.CmsConfig;
import com.mzx.framework.model.cms.CmsConfigModel;
import com.mzx.framework.model.cms.CmsConfigTemplate;
import com.mzx.framework.model.cms.response.CmsCode;
import com.mzx.server.managecms.dao.CmsPageConfigRepository;
import com.mzx.server.managecms.service.IPageConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:38
 */
@Service
public class PageConfigServiceImpl implements IPageConfigService {

    @Autowired
    private CmsPageConfigRepository repository;

    @Override
    public CmsConfig get(String id) {

        if(StringUtils.isEmpty(id)){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        CmsConfig config = null;

        Optional<CmsConfig> o = repository.findById(id);
        if( o.isPresent() ){
            CmsConfig cmsConfig = o.get();
            if(ObjectUtils.isEmpty(cmsConfig)){
                // 如果查询出来的值是空  抛出异常
                ThrowException.exception(CmsCode.CMS_NOT_THIS_CONFIG);
            }
           config = cmsConfig;
        }

        return config;
    }

    @Override
    public QueryResponseResult get() {

        QueryResult<CmsConfigTemplate> result = new QueryResult<>();

        List<CmsConfig> all = repository.findAll();
        if( all.size() <= 0){
            ThrowException.exception(CmsCode.CMS_NOT_THIS_CONFIG);
        }

        List<CmsConfigTemplate> list = new ArrayList<>();
        List<String> model_values = new ArrayList<>();
        for (CmsConfig cmsConfig : all) {
            List<CmsConfigModel> model = cmsConfig.getModel();
            for (CmsConfigModel cmsConfigModel : model) {

                CmsConfigTemplate template = new CmsConfigTemplate();
                template.setId(cmsConfig.getId());
                template.setName(cmsConfigModel.getName());
                template.setModel_key(cmsConfigModel.getKey());
                
                template.setModel_mapValue(cmsConfigModel.getMapValue());
                template.setModel_name(cmsConfigModel.getName());
                template.setModel_url(cmsConfigModel.getUrl());
                //template.setModel_value(cmsConfigModel.getValue());
                model_values.add(cmsConfigModel.getValue());
                list.add(template);
            }
        }
        result.setList(list);
        result.setTotal((long) list.size());
        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }

    @Override
    public ResponseResult add(CmsConfig cmsConfig) {

        if( ObjectUtils.isEmpty(cmsConfig) ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        repository.save(cmsConfig);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult delete(String id) {

        if( StringUtils.isEmpty(id) ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        repository.deleteById(id);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult update(String id,CmsConfig cmsConfig) {

        if( (StringUtils.isEmpty(id)) && (ObjectUtils.isEmpty(cmsConfig))){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        Optional<CmsConfig> o = repository.findById(id);
        if( o.isPresent() ){
            CmsConfig cms = o.get();
            cms.setName(cmsConfig.getName());
            cms.setModel(cmsConfig.getModel());
            repository.save(cms);
        }else {
            ThrowException.exception(CommonCode.FAIL);
        }

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public QueryResponseResult getValue() {

        QueryResult<String> result = new QueryResult<>();

        List<CmsConfig> all = repository.findAll();
        if( all.size() <= 0){
            ThrowException.exception(CmsCode.CMS_NOT_THIS_CONFIG);
        }

        List<String> model_values = new ArrayList<>();
        for (CmsConfig cmsConfig : all) {
            List<CmsConfigModel> model = cmsConfig.getModel();
            for (CmsConfigModel cmsConfigModel : model) {
                model_values.add(cmsConfigModel.getValue());
            }
        }

        result.setList(model_values);
        result.setTotal((long) model_values.size());

        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }
}
