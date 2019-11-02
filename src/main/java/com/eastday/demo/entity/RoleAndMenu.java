package com.eastday.demo.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "role_menu")
public class RoleAndMenu {

    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;//角色表id

    @Column(name = "menu_id")
    private Integer menuId;//权限表id

    @Column(name = "create_time")
    private Date createTime;//创建时间

}
