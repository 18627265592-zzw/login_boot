package com.eastday.demo.dao;

import com.eastday.demo.entity.Mobile;
import com.eastday.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IUserDao {
    /**
     * 添加用户
     * @param u
     */
    public void addUser(User u);

    /**
     * 添加手机号信息
     * @param mobile
     */
    public void addMobile(Mobile mobile);

    /**
     * 查询用户
     * @return
     */
    public User findUserByPhone(String phone);

    /**
     * 刷新最后登录时间,验证码使用状态,保存token
     * @param user
     */
    public void refreshLastLoginTime(User user);

    /**
     * 查询手机号+验证码在数据库是否存在对应数据
     * @param map
     * @return
     */
    public Mobile findUserAndCode(Map map);

    /**
     * 更新验证码信息
     * @param mobile
     */
    public void updMobileInfo(Mobile mobile);
}
