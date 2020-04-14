package com.mzx.framework.model.course.response;

import com.google.common.collect.ImmutableMap;
import com.mzx.common.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author ZhenXinMa
 * @date 2020/3/24 16:11
 */
@ToString
public enum CourseCode implements ResultCode {
    COURSE_DENIED_DELETE(false,31001,"删除课程失败，只允许删除本机构的课程！"),
    COURSE_PUBLISH_PERVIEWISNULL(false,31002,"还没有进行课程预览！"),
    COURSE_PUBLISH_CDETAILERROR(false,31003,"创建课程详情页面出错！"),
    COURSE_PUBLISH_COURSEIDISNULL(false,31004,"课程Id为空！"),
    COURSE_PUBLISH_VIEWERROR(false,31005,"发布课程视图出错！"),
    COURSE_MEDIS_URLISNULL(false,31101,"选择的媒资文件访问地址为空！"),
    COURSE_MEDIS_NAMEISNULL(false,31102,"选择的媒资文件名称为空！"),
    COURSE_BAD_PARAMETER_IS_NULL(false,331103,"请重新选择参数，该参数对应的信息不存在"),
    COURSE_CATEGORY_IS_NULL(false,331104,"课程分类为空,请先增加课程分类!"),
    COURSE_PIC_IS_EMPTY(false,331105,"课程对应的图片为空,请先上传课程图片!"),
    COURSE_PIC_IS_HAVE_MULTIPLE_IMAGES(false,331106,"当前课程对应图片存在多个!");

    boolean success;

    int code;

    String message;

    LocalDateTime time;

    private CourseCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
        this.time = LocalDateTime.now();
    }
    private static final ImmutableMap<Integer, CourseCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, CourseCode> builder = ImmutableMap.builder();
        for (CourseCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
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
