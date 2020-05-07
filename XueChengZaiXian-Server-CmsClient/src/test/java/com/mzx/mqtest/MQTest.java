package com.mzx.mqtest;

import com.mzx.server.cmsclient.ServerCmsClientApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 14:03
 */
@SpringBootTest(classes = {ServerCmsClientApp.class})
@RunWith(SpringRunner.class)
public class MQTest {


    @Test
    public void t1()
    {
        double x = 9.997;
        int nx = (int) x;
        System.out.println(nx);
        System.out.println(1);
    }

}
