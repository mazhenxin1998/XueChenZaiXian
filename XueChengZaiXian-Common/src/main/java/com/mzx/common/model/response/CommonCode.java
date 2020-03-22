package com.mzx.common.model.response;

import lombok.ToString;

import javax.validation.constraints.AssertFalse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:58
 *
 *      响应结果的一个枚举
 *
 *
 */
@ToString
public enum  CommonCode implements ResultCode {

    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    BAD_PARAMETERS(false,10003,"抱歉，请求参数错误 请重试"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
    THIS_PARAMETERS_NOT_LEGAL(false,10004,"抱歉，您传的参数所对应的信心不存在!");
    //    private static ImmutableMap<Integer, CommonCode> codes ;

    /**
     *  操作类型
     */
    boolean success;

    /**
     * 错误代码
     */
    int code;

    /**
     * 提示信息
     */
    String message;

    LocalDateTime time;

    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public LocalDateTime time() {
        return time;
    }


}
