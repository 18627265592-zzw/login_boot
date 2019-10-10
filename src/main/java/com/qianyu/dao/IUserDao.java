package com.qianyu.dao;

import com.qianyu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {
    /**
     * 添加用户
     * @param u
     */
    public void addUser(User u);

    /**
     * 查询用户
     * @return
     */
    public User findUserByPhone(String phone);

    /**
     * 刷新最后登录时间,验证码使用状态
     * @param phone
     */
    public void refreshLastLoginTime(String phone);

    /**
     * 查询手机号+验证码在数据库是否存在对应数据
     * @param User
     * @return
     */
    public User findUserAndCode(User User);

    /**
     * 更新验证码信息
     * @param User
     */
    public void updCodeInfo(User User);
}
