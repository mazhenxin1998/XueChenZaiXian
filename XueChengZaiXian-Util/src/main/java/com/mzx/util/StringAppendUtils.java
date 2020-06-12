package com.mzx.util;

/**
 * @author ZhenXinMa
 * @date 2020/5/17 19:30
 */
public class StringAppendUtils {

    public static String appendString(StringBuilder builder, String... args) {

        for (String arg : args) {

            builder.append(arg);
        }

        return builder.toString();

    }

}
