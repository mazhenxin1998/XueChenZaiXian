package com.mzx.framework.model.learning.response;


import com.mzx.common.model.response.ResultCode;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
public enum LearningCode implements ResultCode {
    LEARNING_GETMEDIA_ERROR(false,23001,"获取学习地址失败！");

    boolean success;
    int code;
    String message;
    private LearningCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
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
        return null;
    }
}
