package com.mzx.server.managercms.time;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author ZhenXinMa
 * @date 2020/2/6 12:52
 */
public class TimeTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(localDateTime);
        System.out.println(localTime);
    }
}
