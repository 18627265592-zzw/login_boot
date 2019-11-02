package com.eastday.demo.dao;

import com.eastday.demo.entity.UserAndRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IUserAndRoleDao extends Mapper<UserAndRole>,MySqlMapper<UserAndRole> {
}
