package com.mzx.framework.model.course.ext;

import com.mzx.framework.model.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @bIds  二级分类.
 * @cIds  三级分类.
 * @author ZhenXinMa
 * @date 2020/3/23 10:43
 */
@Data
@ToString
public class CategoryParameter extends Category {

    List<String> bIds;

    List<String> cIds;
}
