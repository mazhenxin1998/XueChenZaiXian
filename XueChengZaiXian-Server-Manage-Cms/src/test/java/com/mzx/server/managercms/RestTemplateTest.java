package com.mzx.server.managercms;

import com.mzx.framework.model.cms.CmsConfig;
import com.mzx.server.managecms.ServerManageCmsApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ZhenXinMa
 * @date 2020/2/11 15:59
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    @Autowired
    RestTemplate template;

    @Test
    public void t1(){

        ResponseEntity<Map> entity = template.getForEntity("http://localhost:31001/cms/config/get/5a795d82dd573c3574ee3360", Map.class);
        System.out.println(entity);

        Map body = entity.getBody();
        Object model = body.get("model");
        List<Object> list = new ArrayList<>();
        list = (List<Object>) model;
        for (Object o : list) {
            System.out.println(o);

        }
        System.out.println(1);
    }

    @Test
    public void t2(){
        Map<String,Object> map = new HashMap<>();
    }

}
