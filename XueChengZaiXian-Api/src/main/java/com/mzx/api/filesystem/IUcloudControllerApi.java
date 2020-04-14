package com.mzx.api.filesystem;

import com.mzx.common.model.request.RequestData;
import com.mzx.common.model.response.ResponseData;
import com.mzx.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author ZhenXinMa
 * @date 2020/4/5 20:39
 */
@Api(value = "文件服务接口")
public interface IUcloudControllerApi {

    @ApiOperation(value = "增加课程图片接口")
    ResponseData addCoursePhoto(MultipartFile file, HttpServletRequest request);

}
