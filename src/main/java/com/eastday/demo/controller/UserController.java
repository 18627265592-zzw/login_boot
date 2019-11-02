package com.eastday.demo.controller;

import com.eastday.demo.entity.RetDto;
import com.eastday.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *手机验证码登录
     * @param phone
     * @param code
     * @return
     */
    @PostMapping(value="smsLogin/{phone}/{code}")
    public RetDto smsLogin(@PathVariable String phone,@PathVariable String code, HttpServletResponse response){
        return userService.smsLogin(phone,code,response);
    }

    /**
     * 模拟发送短信验证码
     * @param phone
     * @return
     */
    @PostMapping(value="sendCode/{phone}")
    public RetDto sendCode(@PathVariable String phone){
        return userService.sendCode(phone);
    }


    /**
     * 验证图像验证码
     * @param code
     * @return
     */
    @GetMapping(value = "checkKaptcha/{code}")
    public RetDto kaptchaLogin(@PathVariable String code, HttpServletRequest request){
        return userService.checkKaptcha(code,request);
    }


}
