package com.eastday.demo.service;

import com.eastday.demo.dao.IMobileDao;
import com.eastday.demo.dao.IUserDao;
import com.eastday.demo.entity.Mobile;
import com.eastday.demo.entity.RetDto;
import com.eastday.demo.entity.User;
import com.eastday.demo.util.DateUtils;
import com.eastday.demo.util.DesUtil;
import com.eastday.demo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    /**
     *手机验证码登录
     * @param phone
     * @param code
     * @return
     */
    public RetDto smsLogin(String phone,String code) {
        //验证手机号
        if(!checkPhone(phone)){
            return new RetDto(false,1,null);// 1：手机号有误
        }
        //验证短信验证码
        Mobile mobile=findUserAndCode(phone,code);
        if(mobile == null){
            return new RetDto(false,2,null);// 2:验证码有误
        }else{
            int MM =(int)DateUtils.getDistanceMinTimes(new Date(),mobile.getMobileSendTime()); // 共计分钟数*/
            System.out.println("间隔时间------"+MM+"分钟");
            if (MM > jwt.EFFECTIVE_TIME) {// 时间间隔大于五分钟 验证码失效
                return new RetDto(false,3,null); //3:验证码失效
            }else if(mobile.getMobileUsable()==1){//验证码已使用
                return new RetDto(false,4,null); //4:验证码已使用
            }else{
                User user = refreshLastLoginTime(phone);
                updateMobileUsable(phone);
                Map<String,Object> map=new HashMap<>();
                map.put("uid",user.getUid());
                map.put("accessToken",user.getAccessToken());
                return new RetDto(true,0,map);
            }
        }
    }

    /**
     * 模拟发送短信验证码
     * @param phone
     * @return
     */
    public RetDto sendCode(String phone) {
        if(!checkPhone(phone)){
            return new RetDto(false,1,null);
        }else{
            String random = jwt.NumberCode(6);
            log.debug("验证码————"+random);
            //查询数据库手机号是否存在
            User user=findUserByPhone(phone);
            if(user!=null){
                updateMobileInfo(phone,random);
                log.debug("发送成功");
                return new RetDto(true,0,null);
            }else{
                log.debug("发送成功");
                int uid = addUser(phone);
                addMobile(uid,phone,random);
                return new RetDto(true,0,null);
            }
        }
    }

    /**
     * 验证图像验证码
     * @param code
     * @return
     */
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
        user.setUserPhone(des.encrypt(phone));
        return userDao.selectOne(user);
    }

    public void updateMobileInfo(String phone,String code){
        Mobile mobile1=new Mobile();
        mobile1.setMobilePhone(phone);
        Mobile mobile=mobileDao.selectOne(mobile1);
        mobile.setMobileCode(code);
        mobile.setMobileSendTime(new Date());
        mobile.setMobileUsable(0);
        mobileDao.updateByPrimaryKeySelective(mobile);
    }

    public int addUser(String phone){
        User user=new User();
        user.setUserPhone(des.encrypt(phone));
        user.setUserCreatTime(new Date());
        userDao.insertSelective(user);
        return user.getUid();
    }

    public void addMobile(int uid,String phone,String code){
        Mobile mobile=new Mobile();
        mobile.setUid(uid);
        mobile.setMobilePhone(phone);
        mobile.setMobileCode(code);
        mobile.setMobileSendTime(new Date());
        mobile.setMobileUsable(0);
        mobileDao.insertSelective(mobile);
    }

    public Mobile findUserAndCode(String phone,String code){
        Mobile mobile=new Mobile();
        mobile.setMobilePhone(phone);
        mobile.setMobileCode(code);
        return mobileDao.selectOne(mobile);
    }

    public User refreshLastLoginTime(String phone){
        User user = new User();
        user.setUserPhone(des.encrypt(phone));
        User user2 = userDao.selectOne(user);
        String access_token=jwt.encode(user,7200000);//2小时
        user2.setAccessToken(access_token);
        user2.setUserLastLoginTime(new Date());
        userDao.updateByPrimaryKeySelective(user2);
        return user2;
    }

    public void updateMobileUsable(String phone){
        Mobile mobile=new Mobile();
        mobile.setMobilePhone(phone);
        Mobile mobile2=mobileDao.selectOne(mobile);
        mobile2.setMobileUsable(1);
        mobileDao.updateByPrimaryKeySelective(mobile2);
    }

    public User query(Integer id){
       return userDao.selectByPrimaryKey(2);
    }





}
