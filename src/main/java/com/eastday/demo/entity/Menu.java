package com.eastday.demo.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "menu")
@Data
public class Menu {

    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;//主键

    @Column(name = "permission_name")
    private String menuName;//权限名称

    @Column(name = "permission_code")
    private String menuCode;//权限代码

    @Column(name = "parent_id")
    private Integer parentId;//父节点ID

    @Column(name = "lever")
    private Integer lever;//菜单级别

    @Column(name = "url")
    private String url;//地址

    @Column(name = "create_time")
    private Date createTime;//创建时间

    @Transient
    private List<Menu> childMenus;//子菜单


}
