package com.eastday.demo.dao;

import com.eastday.demo.entity.RoleAndMenu;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IRoleAndMenuDao extends Mapper<RoleAndMenu>,MySqlMapper<RoleAndMenu> {
}
