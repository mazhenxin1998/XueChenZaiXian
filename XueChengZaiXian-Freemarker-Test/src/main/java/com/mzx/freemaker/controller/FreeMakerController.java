package com.mzx.freemaker.controller;

import com.alibaba.fastjson.JSON;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.framework.model.course.CourseBase;
import com.mzx.framework.model.course.CoursePic;
import com.mzx.framework.model.course.CourseView;
import com.mzx.freemaker.feignservice.CmsCourseService;
import com.mzx.freemaker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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

    @Resource
    private CmsCourseService cmsCourseService;

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

    @GetMapping(value = "/course/{id}")
    public String course(Map<String,Object> map, @PathVariable(value = "id") String id){

        /*courseBase
        * courseMarket
        * coursePic
        * 4028e581617f945f01617f9dabc40000
        * */
        CourseView view = cmsCourseService.courseView(id);
        CourseBase base = view.getCourseBase();
        map.put("courseBase",view.getCourseBase());
        map.put("courseMarket",view.getCourseMarket());
        if( view.getCoursePic() == null ){

            CoursePic coursePic = new CoursePic();
            coursePic.setCourseid("4028e581617f945f01617f9dabc40000");
            coursePic.setFileName("XXX");
            coursePic.setStatus("1");
            coursePic.setPic("http://xuechengzaixian.cn-bj.ufileos.com/f2090885-354a-425d-b2ef-89e0db00a2ba.png?UCloudPublicKey=TOKEN_e8e90452-6c00-4a80-9f77-c0e2ef6a1d7c&Signature=vtdwZmsvXG9ovSwKfk8mWatxtfY%3D&Expires=3413654604");
            map.put("coursePic",coursePic);
        }else{

            map.put("coursePic",view.getCoursePic());
        }

        map.put("teachplanNode",view.getTeachPlanNode());

        return "course";
    }

}