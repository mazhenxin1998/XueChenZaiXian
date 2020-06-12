package com.mzx;

import com.mzx.mediaprocessor.SpringApplicationMediaProcessorApp;
import com.mzx.util.Mp4VideoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhenXinMa
 * @date 2020/5/18 21:57
 */
@SpringBootTest(classes = {SpringApplicationMediaProcessorApp.class})
@RunWith(SpringRunner.class)
public class ProcessBuilderTest {

    /**
     * 测试在Java应用程序中调用第三方程序，例如CMD.
     */
    @Test
    public void t1() {

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("ipconfig");
        // 将标准输入流和错误输入流合并. 通过标准输入流来获取信息.
        builder.redirectErrorStream(true);
        try {

            Process process = builder.start();
            // 获取输入流.
            InputStream inputStream = process.getInputStream();
            // 转成字符输入浏.
            InputStreamReader reader = new InputStreamReader(inputStream, "gbk");
            int len = -1;
            char[] b = new char[1024];
            StringBuffer buffer = new StringBuffer();
            while ((reader.read(b)) != -1) {

                // 每次将信息存入到 char b数组中.
                buffer.append(b);
                System.out.println(buffer.toString());
            }

            reader.close();
            inputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * 测试FFmpeg
     */
    @Test
    public void t2() {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffmpeg -i D:\\Server\\FFmpeg\\test\\lucene.avi -c:v libx264 -s 1280x720 -pix_fmt yuv420p -b:a 63k -b:v 753k -r 18 D:\\Server\\FFmpeg\\test\\13.mp4");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void t3() {

        /*
        *
        * ffmpeg ‐i lucene.avi ‐c:v libx264 ‐s 1280x720 ‐pix_fmt yuv420p ‐b:a 63k ‐b:v 753k ‐r 18
.\lucene.mp4
*
*
* D:\Server\FFmpeg\test
        * */

        try {
            Process exec = Runtime.getRuntime().exec("ffmpeg -i D:\\Server\\FFmpeg\\test\\lucene.avi -c:v libx264 -s 1280x720 -pix_fmt yuv420p -b:a 63k -b:v 753k -r 18 D:\\Server\\FFmpeg\\test\\12.mp4");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void t4(){


        String ffmpeg = "ffmpeg";
        String avPath = "D:\\Server\\FFmpeg\\test\\lucene.avi";
        String mp4Folder = "D:\\Server\\FFmpeg\\test\\";
        String mp4Name = "14.mp4";
        Mp4VideoUtil util = new Mp4VideoUtil(ffmpeg,avPath,mp4Name,mp4Folder);
        util.generateMp4();



    }

}
