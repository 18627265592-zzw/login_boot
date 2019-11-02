package com.eastday.demo.entity;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user_role")
public class UserAndRole {

    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;//用户id

    @Column(name = "role_id")
    private Integer roleId;//角色id

    @Column(name = "create_time")
    private Date createTime;//创建时间
}
