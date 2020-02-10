package com.mzx.common.model.response;

import java.time.LocalDateTime;


/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:57
 *
 *      查询结果状态码
 *
 */
public interface ResultCode {

    boolean success();

    int code();

    String message();

    LocalDateTime time();
}
