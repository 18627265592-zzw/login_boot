package com.eastday.demo.service;

import com.eastday.demo.entity.RetDto;
import com.eastday.demo.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {


    public RetDto SmsLogin(String phone,String code);

    public RetDto sendCode(String phone);

    public RetDto checkKaptcha(String code, HttpServletRequest request);

}
