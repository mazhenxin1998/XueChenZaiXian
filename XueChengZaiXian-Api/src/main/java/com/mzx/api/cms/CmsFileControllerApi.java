package com.mzx.api.cms;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhenXinMa
 * @date 2020/2/12 21:56
 */
@Api(value = "CmsFile查询接口")
public interface CmsFileControllerApi {

    /**
     *  增加CmsTemplate File文件接口.
     *
     *  文件存在MongoDB中的GridFS文件存储系统.
     *  其中文件名字就是上传的时候文件的名字.
     * @param file 要上传的模板文件.
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

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    QueryResponseResult get(String id);

    /**
     *  查询所有File
     * @return
     */
    QueryResponseResult get();

    /**
     *  通过filename查询
     * @param name
     * @return
     */
    QueryResponseResult getByName(String name);

}
