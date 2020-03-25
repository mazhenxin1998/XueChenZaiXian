package com.mzx.framework.model.course.ext;

import com.mzx.framework.model.course.TeachPlan;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @bIds  二级分类ids.
 * @cIds  三级分类ids.
 * @author ZhenXinMa
 * @date 2020/3/23 10:49
 */
@Data
@ToString
public class TeachPlanParameter extends TeachPlan {

    List<String> bIds;

    List<String> cIds;
}
