package com.mzx;

import com.mzx.search.SpringApplicationSearchApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhenXinMa
 * @date 2020/6/4 20:27
 */
@SpringBootTest(classes = {SpringApplicationSearchApp.class})
@RunWith(SpringRunner.class)
public class UtilTest {

    @Test
    public void t1() {

        //D:/Server/FFmpeg/test/c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8
        String s1 = "D:/Server/FFmpeg/test/c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8";
        String[] split = s1.split("/");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {

            if (i >= 4) {

                builder.append(split[i]);
                if (i != 8) {

                    builder.append("/");
                }

            }

        }

        System.out.println(builder.toString());
        System.out.println(1);


    }


}
