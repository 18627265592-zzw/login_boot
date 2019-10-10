package com.qianyu.service;

import com.qianyu.entity.RetDto;
import com.qianyu.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {


    /**
     *手机号登录
     * @param user
     * @return
     */
    public RetDto login(User user);

    /**
     * 模拟发送短信验证码
     * @param phone
     * @return
     */
    public RetDto sendCode(String phone);

}
