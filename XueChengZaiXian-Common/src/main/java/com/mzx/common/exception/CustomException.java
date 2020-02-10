package com.mzx.common.exception;

import com.mzx.common.model.response.ResultCode;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 16:42
 *
 *       自定义异常类型
 *
 */
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super("错误信息:"+resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return this.resultCode;
    }

}
