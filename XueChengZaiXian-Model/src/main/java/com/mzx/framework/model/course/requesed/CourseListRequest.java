package com.mzx.framework.model.course.requesed;

import com.mzx.common.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 22:20
 */
@Data
@ToString
public class CourseListRequest extends RequestData {
    /**
     * 公司ID.
     */
    private String companyId;
}
