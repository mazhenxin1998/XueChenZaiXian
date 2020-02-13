package com.mzx.server.managecms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsTemplate;
import com.mzx.framework.model.cms.response.CmsCode;
import com.mzx.server.managecms.dao.CmsTemplateRepository;
import com.mzx.server.managecms.service.ITemplateService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:10
 */
@Service
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private CmsTemplateRepository repository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Override
    public QueryResponseResult get() {
        List<CmsTemplate> all = repository.findAll();
        QueryResult<CmsTemplate> template = new QueryResult<CmsTemplate>();
        template.setList(all);
        template.setTotal((long) all.size());
        return new QueryResponseResult(CommonCode.SUCCESS,template);
    }

    @Override
    public ResponseResult add(CmsTemplate cmsTemplate) {
        System.out.println(cmsTemplate);
        if(ObjectUtils.isEmpty(cmsTemplate)){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        cmsTemplate.setTemplateId(null);
        repository.save(cmsTemplate);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult delete(String id) {

        if(StringUtils.isEmpty(id)){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }
        repository.deleteById(id);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult update(String id, CmsTemplate cmsTemplate) {

        if( StringUtils.isEmpty(id) ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        Optional<CmsTemplate> o = repository.findById(id);
        if( o.isPresent() ){
            CmsTemplate template = o.get();
            if(template == null){
                ThrowException.exception(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
            }else{
                template.setTemplateId(id);
                template.setTemplateFileId(cmsTemplate.getTemplateFileId());
                template.setSiteId(cmsTemplate.getSiteId());
                template.setTemplateName(cmsTemplate.getTemplateName());
                template.setTemplateParameter(cmsTemplate.getTemplateParameter());
                repository.save(template);
            }
        }else{
            ThrowException.exception(CommonCode.FAIL);
        }

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult upload(MultipartFile file,HttpServletRequest request) {

        String file_name = file.getOriginalFilename();
        System.out.println(2);
        try {
            InputStream inputStream = file.getInputStream();
            ObjectId objectId = gridFsTemplate.store(inputStream, file_name);
            System.out.println(objectId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseResult(CommonCode.SUCCESS);
    }


}
