package com.mzx.framework.model.cms.response;

import com.mzx.common.model.response.ResultCode;
import com.sun.org.apache.bcel.internal.generic.FADD;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ZhenXinMa
 * @date 2020/2/4 21:51
 */
public enum CmsCode implements ResultCode {

    CMS_ADDPAGE_EXISTSNAME(false,24001,"页面名称已存在！"),
    CMS_GENERATEHTML_DATAURLISNULL(false,24002,"从页面信息中找不到获取数据的url！"),
    CMS_GENERATEHTML_DATAISNULL(false,24003,"根据页面的数据url获取不到数据！"),
    CMS_GENERATEHTML_TEMPLATEISNULL(false,24004,"页面模板为空！"),
    CMS_GENERATEHTML_HTMLISNULL(false,24005,"生成的静态html为空！"),
    CMS_GENERATEHTML_SAVEHTMLERROR(false,24005,"保存静态html出错！"),
    CMS_COURSE_PERVIEWISNULL(false,24007,"预览页面为空！"),
    CMS_PAGE_NOT_FIND(false,24008,"该页面不存在"),
    CMS_NOT_THIS_CONFIG(false,24009,"该页面配置不存在");

    /**
     *  操作代码
     */
    boolean success;

    /**
     *  操作代码
     */
    int code;

    /**
     *  提示信息
     */
    String message;

    LocalDateTime time;

    private CmsCode(boolean success, int code, String message){
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
