package com.mzx.framework.model.course.ext;

import com.mzx.framework.model.course.TeachPlan;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 10:48
 */
@Data
@ToString
public class TeachPlanNode extends TeachPlan {

    List<TeachPlanNode> children;
    private String mediaId;
    private String mediaFileoriginalname;

}
