package com.eastday.demo.dao;

import com.eastday.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao extends tk.mybatis.mapper.common.Mapper<User> {

}
