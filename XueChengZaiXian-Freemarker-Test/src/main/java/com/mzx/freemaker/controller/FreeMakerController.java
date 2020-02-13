package com.mzx.freemaker.controller;

import com.mzx.freemaker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 21:56
 */
@Controller
@RequestMapping(value = "/free")
public class FreeMakerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test1")
    public String test(Map<String,Object> map){

        map.put("name","黑马");

        Student s1 = new Student();
        s1.setAge(1)
                .setMoney(1F)
                .setName("张三")
                .setBirthday(new Date());

        Student s2 = new Student();
        s2.setAge(2)
                .setName("李四")
                .setMoney(2F)
                .setBirthday(new Date());

        List<Student> friends = new ArrayList<>();
        friends.add(s1);
        friends.add(s2);

        Map<String,Student> m = new HashMap<>();
        m.put("s1",s1);
        m.put("s2",s2);

        map.put("friends",friends);
        map.put("map",m);

        return "test1";
    }

    @GetMapping(value = "/t1")
    public String t1(Map<String,Object> map){

        ResponseEntity<Map> entity = restTemplate.getForEntity("http://localhost:31001/cms/config/get/5a791725dd573c3574ee333f", Map.class);
        Map body = entity.getBody();
        map.putAll(body);
        System.out.println(1);
        return "index_banner";
    }

}