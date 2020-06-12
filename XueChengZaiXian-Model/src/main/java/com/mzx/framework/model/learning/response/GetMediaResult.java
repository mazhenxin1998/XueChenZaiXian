package com.mzx.framework.model.learning.response;

import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/6/3 22:23
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetMediaResult extends ResponseResult {

    private String fileUrl;

    public GetMediaResult(ResultCode resultCode,String fileUrl){
        super(resultCode);
        this.fileUrl = fileUrl;
    }




}
