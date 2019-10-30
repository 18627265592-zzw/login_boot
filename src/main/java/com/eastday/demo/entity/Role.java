package com.eastday.demo.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Table(name = "role")
@Data
public class Role {

    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer roleId;//主键

    @Column(name = "role_name")
    private String roleName;//角色名

    @Column(name = "role_code")
    private String roleCode;//角色代码

    @Column(name = "create_time")
    private Date createTime;//创建时间
}
