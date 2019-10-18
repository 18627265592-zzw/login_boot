package com.eastday.demo.service;

import com.eastday.demo.dao.IMobileDao;
import com.eastday.demo.dao.IUserDao;
import com.eastday.demo.entity.Mobile;
import com.eastday.demo.entity.RetDto;
import com.eastday.demo.entity.User;
import com.eastday.demo.util.DesUtil;
import com.eastday.demo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service(value = "userService")
@Slf4j
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IMobileDao mobileDao;

    @Autowired
    private DesUtil des;

    @Autowired
    private JwtUtils jwt;


    public RetDto SmsLogin(String phone,String code) {
        //验证手机号
        if(!checkPhone(phone)){
            return new RetDto(false,1,null);// 1：手机号有误
        }
        //验证短信验证码
        Mobile mobile=findUserAndCode(phone,code);
        if(mobile == null){
            return new RetDto(false,2,null);// 2:验证码有误
        }else{
            long endT=mobile.getSend_line();//验证码发送时间
            long startT = (new Date()).getTime();
            long ss = (startT - endT) / (1000); // 共计秒数
            int MM = (int) ss / 60; // 共计分钟数

            if (MM > jwt.EFFECTIVE_TIME) {// 时间间隔大于五分钟 验证码失效
                return new RetDto(false,3,null); //3:验证码失效
            }else if(mobile.getUsable()==1){//验证码已使用
                return new RetDto(false,4,null); //4:验证码已使用
            }else{
                String access_token = refreshLastLoginTime(phone);
                updMobileUsable(phone);
                return new RetDto(true,0,access_token);
            }
        }
    }


    public RetDto sendCode(String phone) {
        if(!checkPhone(phone)){
            return new RetDto(false,1,null);
        }else{
            String random = jwt.NumberCode(6);
            log.debug("验证码————"+random);
            //查询数据库手机号是否存在
            User user=findUserByPhone(phone);
            if(user!=null){
                Mobile mobile=new Mobile();
                mobile.setPhone(phone);
                updMobileInfo(mobile,random);
                log.debug("发送成功");
                return new RetDto(true,0,null);
            }else{
                log.debug("发送成功");
                addUser(phone);
                addMobile(phone,random);
                return new RetDto(true,0,null);
            }
        }
    }


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

    public boolean checkPhone(String phone){
        if(jwt.isMobile(phone)){
            return true;
        }else{
            return false;
        }
    }

    public User findUserByPhone(String phone){
        User user=new User();
        user.setPhone(des.encrypt(phone));
        return userDao.selectOne(user);
    }

    public void updMobileInfo(Mobile mobile1,String code){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Mobile mobile=mobileDao.selectOne(mobile1);
        mobile.setCode(code);
        mobile.setSend_time(sdf.format(new Date()));
        mobile.setSend_line((new Date()).getTime());
        mobile.setUsable(0);
        mobileDao.updateByPrimaryKeySelective(mobile);
    }

    public void addUser(String phone){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user=new User();
        user.setPhone(des.encrypt(phone));
        user.setCreat_time(sdf.format(new Date()));
        userDao.insertSelective(user);
    }

    public void addMobile(String phone,String code){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Mobile mobile=new Mobile();
        mobile.setPhone(phone);
        mobile.setCode(code);
        mobile.setSend_time(sdf.format(new Date()));
        mobile.setSend_line((new Date()).getTime());
        mobile.setUsable(0);
        mobileDao.insertSelective(mobile);
    }

    public Mobile findUserAndCode(String phone,String code){
        Mobile mobile=new Mobile();
        mobile.setPhone(phone);
        mobile.setCode(code);
        return mobileDao.selectOne(mobile);
    }

    public String refreshLastLoginTime(String phone){
        User user = new User();
        user.setPhone(des.encrypt(phone));
        User user2 = userDao.selectOne(user);
        String access_token=jwt.encode(user,7200000);//2小时
        user2.setAccess_token(access_token);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user2.setLast_login_time(sdf.format(new Date()));
        userDao.updateByPrimaryKeySelective(user2);
        return access_token;
    }

    public void updMobileUsable(String phone){
        Mobile mobile=new Mobile();
        mobile.setPhone(phone);
        Mobile mobile2=mobileDao.selectOne(mobile);
        mobile2.setUsable(1);
        mobileDao.updateByPrimaryKeySelective(mobile2);
    }





}
