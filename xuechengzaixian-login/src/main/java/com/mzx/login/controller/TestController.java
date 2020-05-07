package com.mzx.login.controller;

import com.mzx.login.bean.Student;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 17:08
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping(value = "/t1")
    public String t1(@RequestBody  Student student) {
        System.out.println(1);
        return this.appendString(new StringBuilder(),student.getId(),"<br>",
                student.getName());
    }


    public String appendString(StringBuilder builder, String... args) {

        for (String arg : args) {

            builder.append(arg);
        }

        return builder.toString();
    }


}
