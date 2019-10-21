package com.eastday.demo.entity;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer uid;//用户id

    @Column(name="user_phone")
    private String userPhone;//手机号

    @Column(name="user_nick_name")
    private String userNickName;//昵称

    @Column(name="unionId")
    private String unionId;//微信唯一标示

    @Column(name="access_token")
    private String accessToken;//身份验证秘钥

    @Column(name="user_status")
    private Integer userStatus;//数据状态（0：已删除  1：存在  ）

    @Column(name="user_creat_time")
    private Date userCreatTime;//注册时间

    @Column(name="user_last_login_time")
    private Date userLastLoginTime;//最后登录时间

    /*@Transient*/

}
