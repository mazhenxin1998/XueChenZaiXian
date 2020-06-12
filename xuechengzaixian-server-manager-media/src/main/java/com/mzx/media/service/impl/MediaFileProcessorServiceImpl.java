package com.mzx.media.service.impl;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.media.MediaFile;
import com.mzx.framework.model.media.request.QueryMediaFileRequest;
import com.mzx.media.dao.IMediaFileRepository;
import com.mzx.media.mq.MediaRabbitMessageProducer;
import com.mzx.media.service.MediaFileProcessorService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.util.resources.cldr.en.CalendarData_en_BW;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/5/20 15:12
 */
@Slf4j
@Service
public class MediaFileProcessorServiceImpl implements MediaFileProcessorService {

    @Resource
    private IMediaFileRepository iMediaFileRepository;

    @Resource
    private MediaRabbitMessageProducer mediaRabbitMessageProducer;

    @Override
    public QueryResponseResult listMediaFile(String page, String size, QueryMediaFileRequest request) {

        int pageInt = Integer.parseInt(page);
        int sizeInt = Integer.parseInt(size);

        pageInt = pageInt - 1;

        MediaFile mediaFile = new MediaFile();
        if (request == null) {

            request = new QueryMediaFileRequest();
        }

        if (!StringUtils.isEmpty(request.getTag())) {

            mediaFile.setTag(request.getTag());
        }

        if (!StringUtils.isEmpty(request.getFileOriginalName())) {

            mediaFile.setFileOriginalName(request.getFileOriginalName());
        }

        if (!StringUtils.isEmpty(request.getProcessStatus())) {

            mediaFile.setProcessStatus(request.getProcessStatus());
        }


        // 设置查询条件.
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("fileOriginalName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("processStatus", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<MediaFile> example = Example.of(mediaFile, matcher);

        Pageable pageable = PageRequest.of(pageInt, sizeInt);
        Page<MediaFile> all = iMediaFileRepository.findAll(example, pageable);
        QueryResult<MediaFile> queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal((long) all.getSize());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public ResponseResult mediaFileProcess(String mediaFileID) {

        Map<String,Object> message = new HashMap<>();
        Map<String,Object> headers = new HashMap<>();
        message.put("mediaID",mediaFileID);
        mediaRabbitMessageProducer.sendMessage(message,headers,mediaFileID);

        return new ResponseResult(CommonCode.SUCCESS);
    }
}
