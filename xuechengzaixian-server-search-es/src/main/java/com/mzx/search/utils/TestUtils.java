package com.mzx.search.utils;

/**
 * @author ZhenXinMa
 * @date 2020/6/4 22:46
 */
public class TestUtils<T> {

    /**
     * 泛型方法. 类型变量放在修饰符(public private protected)后面.
     *
     * @param <T>
     * @return
     */
    public <T> T getMedia(T... a) {

        return a[0];
    }



}
