package com.mzx.framework.model.media.response;


import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import com.mzx.framework.model.media.MediaFile;
import com.mzx.framework.model.media.MediaVideoCourse;
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
public class MediaCourseResult extends ResponseResult {

    public MediaCourseResult(ResultCode resultCode, MediaVideoCourse mediaVideoCourse) {

        super(resultCode);
        this.mediaVideoCourse = mediaVideoCourse;
    }

    MediaFile mediaVideo;
    MediaVideoCourse mediaVideoCourse;
}
