package com.mzx.framework.model.course.ext;

import com.mzx.framework.model.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/3/23 10:42
 */
@Data
@ToString
public class CategoryNode extends Category {

    List<CategoryNode> children;

}
