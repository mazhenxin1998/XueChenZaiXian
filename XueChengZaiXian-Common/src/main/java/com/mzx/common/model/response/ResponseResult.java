package com.mzx.common.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:58
 *
 *       当前响应结果
 *       Result实现类有个枚举
 *
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response {

    /**
     * 当前操作是否成功
     */
    boolean success = SUCCESS;

    /**
     *  当前操作代码
     */
    int code = SUCCESS_CODE;

    String message;

    LocalDateTime time;

    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.time = resultCode.time();
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }


}
