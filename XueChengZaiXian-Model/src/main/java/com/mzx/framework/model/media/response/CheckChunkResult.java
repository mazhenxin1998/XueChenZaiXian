package com.mzx.framework.model.media.response;


import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ZhenXinMa
 * @date 2020/5/16 22:23
 */
@Data
@ToString
@NoArgsConstructor
public class CheckChunkResult extends ResponseResult {

    public CheckChunkResult(ResultCode resultCode, boolean fileExist) {

        super(resultCode);
        this.fileExist = fileExist;
    }

    @ApiModelProperty(value = "文件分块存在标记", example = "true", required = true)
    boolean fileExist;
}
