package com.mzx.freemaker.controller;

import com.mzx.freemaker.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 21:56
 */
@Controller
@RequestMapping(value = "/free")
public class FreeMakerController {

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


}
