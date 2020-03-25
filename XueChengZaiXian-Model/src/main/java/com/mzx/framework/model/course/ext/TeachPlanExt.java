package com.mzx.framework.model.course.ext;

import com.mzx.framework.model.course.TeachPlan;
import lombok.Data;
import lombok.ToString;

/**
 * @mediaId  媒资文件id.
 * @mediaFileOriginalName  媒资文件原始名称.
 * @mediaUrl  媒资我呢间访问地址.
 * @author ZhenXinMa
 * @date 2020/3/23 10:45
 */
@Data
@ToString
public class TeachPlanExt extends TeachPlan {

    private String mediaId;

    private String mediaFileOriginalName;

    private String mediaUrl;
}