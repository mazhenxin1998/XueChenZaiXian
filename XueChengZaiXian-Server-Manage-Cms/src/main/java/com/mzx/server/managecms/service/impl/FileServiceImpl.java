package com.mzx.server.managecms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.cms.CmsFile;
import com.mzx.server.managecms.dao.CmsFileRepository;
import com.mzx.server.managecms.service.IFileService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 22:03
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFSBucket bucket;

    @Autowired
    private CmsFileRepository repository;

    @Override
    public ResponseResult add(MultipartFile file, HttpServletRequest request) {

        if( file.isEmpty() ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        String name = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            ObjectId objectId = template.store(inputStream, name);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public QueryResponseResult getName(String name) {

        if( StringUtils.isEmpty(name)){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        QueryResult<CmsFile> result = new QueryResult<>();
        List<CmsFile> files = repository.findByFilename(name);
        result.setList(files);
        result.setTotal((long) files.size());

        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }

    @Override
    public QueryResponseResult get(String id) {

        return null;
    }

    @Override
    public QueryResponseResult get() {

        QueryResult<CmsFile> result = new QueryResult<>();
        List<CmsFile> all = repository.findAll();
        result.setList(all);
        result.setTotal((long) all.size());

        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }
}
