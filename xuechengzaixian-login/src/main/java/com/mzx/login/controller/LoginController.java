package com.mzx.login.controller;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.login.bean.User;
import com.mzx.login.dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhenXinMa
 * @date 2020/4/10 16:26
 */
@RestController
@RequestMapping(value = "/ucenter")
public class LoginController {

    @Resource
    private UserDao userDao;


    @GetMapping(value = "/login")
    public ResponseResult login(@RequestBody User user) {

        User u = userDao.getByName(user.getUsername());
        if (u == null) {

            return new ResponseResult(CommonCode.FAIL);
        } else {

            if (user.getPassword().equals(u.getPassword())) {

                return new ResponseResult(CommonCode.SUCCESS);
            } else {

                return new ResponseResult(CommonCode.FAIL);
            }

        }

    }


}
