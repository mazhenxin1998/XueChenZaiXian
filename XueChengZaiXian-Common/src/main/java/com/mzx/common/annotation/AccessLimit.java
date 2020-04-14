package com.mzx.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZhenXinMa
 * @date 2020/4/3 22:51
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    int seconds();
    int masCount();
    boolean needLogin() default true;


}
