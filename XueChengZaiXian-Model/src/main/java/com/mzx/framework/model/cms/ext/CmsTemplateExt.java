package com.mzx.framework.model.cms.ext;

import com.mzx.framework.model.cms.CmsTemplate;
import lombok.Data;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:49
 */
@Data
@ToString
public class CmsTemplateExt extends CmsTemplate {

    //模版内容
    private String templateValue;

}
