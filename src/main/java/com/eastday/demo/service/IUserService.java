package com.eastday.demo.service;

import com.eastday.demo.entity.RetDto;
import com.eastday.demo.entity.User;

public interface IUserService {


    /**
     * 手机号登录
     * @param phone
     * @param code
     * @return
     */
    public RetDto login(String phone,String code);

    /**
     * 模拟发送短信验证码
     * @param phone
     * @return
     */
    public RetDto sendCode(String phone);

}
