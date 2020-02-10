package com.mzx.common.exception;

import com.mzx.common.model.response.ResultCode;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 16:48
 *
 *   对异常类的封装
 *   利用该类的静态方法抛出异常
 */
public class ThrowException {

    public static void exception(ResultCode resultCode){
        throw new CustomException(resultCode);
    }

}
