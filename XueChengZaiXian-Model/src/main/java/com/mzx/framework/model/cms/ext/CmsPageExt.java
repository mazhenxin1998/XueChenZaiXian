package com.mzx.framework.model.cms.ext;

import com.mzx.framework.model.cms.CmsPage;
import lombok.Data;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 21:49
 */
@Data
@ToString
public class CmsPageExt extends CmsPage {
    private String htmlValue;

}
