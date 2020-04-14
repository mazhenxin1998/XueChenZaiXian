package com.mzx.servermanager.feign;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.cms.CmsPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * 通过OpenFeign调用的CmsPagePreview的接口.
 *
 * @author ZhenXinMa
 * @date 2020/4/12 9:34
 */
@Component
@FeignClient(value = "xuechengzaixian-server-manager-cms")
@RequestMapping(value = "/cms")
public interface CmsPagePreviewServiceOpenFeign {

    /**
     * 页面预览.
     *
     * @param id 要预览的页面ID.
     * @return
     */
    @GetMapping(value = "/page/preview/{id}")
    String preview(@PathVariable(value = "id") String id);

    /**
     * 远程调用CmsServer服务,获取到id页面所对应的模型数据Map.
     *
     * @param id 要获取模型数据的页面ID.
     * @return 页面需要的模型数据Map.
     */
    @GetMapping(value = "/get/model/{id}")
    Map getMap(@PathVariable(value = "id") String id);

    /**
     * 远程调用：通过页面ID获取到该页面所对应的模板文件在MongoDB中的ID.
     *
     * @param id 要获取页面模板的页面ID.
     * @return 页面所对应的模板文件ID.
     */
    @GetMapping(value = "/get/fileID/{id}")
    String getFileID(@PathVariable(value = "id") String id);

    /**
     * 远程调用：通过页面ID获取到该页面所对应的页面字符串形式.
     *
     * @param id 要获取页面字符串的页面ID.
     * @return 页面ID所对应的页面的字符串形式, 返回该字符串给浏览器, 浏览器则能正常显示该页面.
     */
    @GetMapping(value = "/get/content/{id}")
    String getContent(@PathVariable(value = "id") String id);

    @PostMapping(value = "/page/add")
    QueryResponseResult add(@RequestBody CmsPage request);

    @GetMapping(value = "/page/get/{name}")
    CmsPage getByName(@PathVariable(value = "name") String name);

    @PostMapping(value = "/page/post/add/page")
    CmsPage addPage(@RequestBody CmsPage cmsPage);

}
