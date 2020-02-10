package com.mzx.common.exception;


import com.google.common.collect.ImmutableMap;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.common.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 16:46
 *
 * @ControllerAdvice  不添加参数则将所有controller中抛出的异常捕获到
 *
 */
@ControllerAdvice
public class ExceptionCatch {

    /**
     *  输出异常日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    /**
     *  谷歌线程安全的Map框架  一旦数据放入到Map中 该数据则不可修改
     */
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS_MAP;
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    /**
     *   将所有在controller中抛出 CustomException 异常的controller执行该controller方法
     *   并响应给客户端,参数为ExceptionHandler 指定的类
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){

        LOGGER.error("Catch Exception ：{}\r\n  exception:",e.getMessage(),e);
        ResultCode resultCode = e.getResultCode();
        return new ResponseResult(resultCode);

    }

    @ExceptionHandler( Exception.class )
    @ResponseBody
    public ResponseResult exception(Exception e){

        LOGGER.error("Catch Exception ：{}\r\n  exception:",e.getMessage(),e);

        if(ObjectUtils.isEmpty(EXCEPTIONS_MAP)) {
            EXCEPTIONS_MAP = builder.build();
        }

        final ResultCode resultCode = EXCEPTIONS_MAP.get(e.getClass());
        final ResponseResult responseResult;

        if (resultCode != null) {
            // 不可捕获的异常 但抛出自定义的错误代码
            responseResult = new ResponseResult(resultCode);
        } else {
            //  服务器异常
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }

    /**
     *   在该类加载的时候将不可捕获异常放入到EXCEPTIONS_MAP
     */
    static {
        builder.put(HttpMessageNotReadableException.class,CommonCode.BAD_PARAMETERS);
    }
}
