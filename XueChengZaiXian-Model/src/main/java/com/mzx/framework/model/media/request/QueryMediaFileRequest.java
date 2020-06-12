package com.mzx.framework.model.media.request;


import com.mzx.common.model.request.RequestData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryMediaFileRequest extends RequestData {

    private String fileOriginalName;
    /**
     * 当前媒资处理的进程.
     */
    private String processStatus;
    private String tag;
}
