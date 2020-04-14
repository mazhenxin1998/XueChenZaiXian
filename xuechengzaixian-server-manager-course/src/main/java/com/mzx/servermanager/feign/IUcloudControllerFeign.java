package com.mzx.servermanager.feign;

import com.mzx.common.model.request.RequestData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;

/**
 * @author ZhenXinMa
 * @date 2020/4/5 21:14
 */
@Component
@FeignClient(value = "xuechengzaixian-filesystem-22100")
@RequestMapping(value = "/file/system")
public interface IUcloudControllerFeign {

    @PostMapping(value = "/addCoursePhoto")
    String addCoursePhoto(Model model);

}
