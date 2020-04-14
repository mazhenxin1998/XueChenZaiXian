package com.mzx.framework.model.course.response;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhenXinMa
 * @date 2020/4/13 14:59
 */
@Data
@ToString
@NoArgsConstructor
public class CoursePublishResult extends ResponseResult implements Serializable {

    String previewUrl;

    public CoursePublishResult(ResultCode resultCode, String previewUrl){

        super(resultCode);
        this.previewUrl = previewUrl;
    }

}
