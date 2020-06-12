package com.mzx.framework.model.media.response;


import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import com.mzx.framework.model.media.MediaFile;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhenXinMa
 * @date 2020/5/16 22:23
 */
@Data
@NoArgsConstructor
public class MediaFileResult extends ResponseResult {

    MediaFile mediaFile;
    public MediaFileResult(ResultCode resultCode, MediaFile mediaFile) {

        super(resultCode);
        this.mediaFile = mediaFile;
    }

}
