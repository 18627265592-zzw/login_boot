package com.eastday.demo.controller;

import com.eastday.demo.entity.RetDto;
import com.eastday.demo.entity.User;
import com.eastday.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     *手机号登录
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value="login")
    public RetDto login(String phone,String code){
        return userService.login(phone,code);
    }

    /**
     * 模拟发送短信验证码
     * @param phone
     * @return
     */
    @RequestMapping(value="sendCode")
    public RetDto sendCode(String phone){
        return userService.sendCode(phone);
    }

}
