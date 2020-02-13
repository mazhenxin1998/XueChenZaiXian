package com.mzx.server.managecms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 22:02
 */
public interface IFileService {

    /**
     *  上传CMS文件
     * @param file
     * @param request
     * @return
     */
    ResponseResult add(MultipartFile file, HttpServletRequest request);

    /**
     *  根据ID删除CMS静态文件
     * @param id
     * @return
     */
    ResponseResult delete(String id);

    QueryResponseResult get(String id);

    /**
     *  查询所有File
     * @return
     */
    QueryResponseResult get();

    QueryResponseResult getName(String name);

}
