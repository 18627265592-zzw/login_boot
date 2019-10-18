package com.eastday.demo.service.impl;

import com.eastday.demo.dao.IUserDao;
import com.eastday.demo.entity.Mobile;
import com.eastday.demo.mapper.UserMapper;
import com.eastday.demo.util.DesUtil;
import com.eastday.demo.entity.RetDto;
import com.eastday.demo.entity.User;
import com.eastday.demo.service.IUserService;
import com.eastday.demo.util.JwtUtils;
import com.eastday.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(value = "userService")
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private DesUtil des;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    private UserMapper userMapper;



    @Override
    public RetDto SmsLogin(String phone,String code) {
        //验证手机号
        if(!jwt.isMobile(phone)){
            return new RetDto(false,1,null);// 1：手机号有误
        }
        //验证短信验证码
        Map<String,String> map = new HashMap<String,String>();
        map.put("phone",des.encrypt(phone));
        map.put("code",code);
        Mobile mobile=userDao.findUserAndCode(map);
        if(mobile == null){
            return new RetDto(false,2,null);// 2:验证码有误
        }else{
            long endT=mobile.getDead_line();//验证码发送时间
            long startT = (new Date()).getTime();
            long ss = (startT - endT) / (1000); // 共计秒数
            int MM = (int) ss / 60; // 共计分钟数

            if (MM > jwt.EFFECTIVE_TIME) {// 时间间隔大于五分钟 验证码失效
                return new RetDto(false,3,null); //3:验证码失效
            }else if(mobile.getUsable()==1){//验证码已使用
                return new RetDto(false,4,null); //4:验证码已使用
            }else{
                User user=userDao.findUserByPhone(des.encrypt(phone));
                //刷新token并存入数据库，更新登录时间，修改验证码状态为已使用
                String access_token=jwt.encode(user,7200000);//2小时
                user.setAccess_token(access_token);
                userDao.refreshLastLoginTime(user);
                //token过期测试
                    /*try {
                        User user2=jwt.decode(token,User.class);
                        if(user2==null){
                            System.out.println("token过期");
                        }else{
                            System.out.println("-----"+user2.getCode());
                            System.out.println("验证通过");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/

                return new RetDto(true,0,access_token);
            }
        }
    }


    @Override
    public RetDto sendCode(String phone) {
        if(!jwt.isMobile(phone)){
            return new RetDto(false,0,null);
        }else{
            String random = jwt.NumberCode(6);
            log.debug("验证码————"+random);
            //System.out.println("验证码————"+random);
            Mobile mobile = new Mobile();
            mobile.setPhone(des.encrypt(phone));
            mobile.setCode(random);
            mobile.setDead_line((new Date()).getTime());
            mobile.setUsable(0);
            //查询数据库手机号是否存在
            User user=userDao.findUserByPhone(des.encrypt(phone));
            if(user!=null){
                userDao.updMobileInfo(mobile);
                log.debug("发送成功");
                return new RetDto(true,0,null);
            }else{
                User user2 = new User();
                user2.setPhone(des.encrypt(phone));
                log.debug("发送成功");
                userDao.addUser(user2);
                userDao.addMobile(mobile);
                return new RetDto(true,0,null);
            }
        }
    }

    @Override
    public RetDto checkKaptcha(String code, HttpServletRequest request) {
        if(code==null || "".equals(code)){
            return new RetDto(false,1,null);//验证码为空
        }
        if(!code.equals(request.getSession().getAttribute("rightCode"))){
            return new RetDto(false,2,null);//验证码有误
        }
        request.getSession().removeAttribute("rightCode");
        return new RetDto(true,0,null);//验证码通过
    }
}
