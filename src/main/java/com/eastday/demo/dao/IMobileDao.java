package com.eastday.demo.dao;

import com.eastday.demo.entity.Mobile;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface IMobileDao extends Mapper<Mobile>,MySqlMapper<Mobile> {

}
