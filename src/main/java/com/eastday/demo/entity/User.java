package com.eastday.demo.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer uid;//用户id

    private String phone;//手机号

    private String nick_name;//昵称

    private String union_id;//微信唯一标示

    private String access_token;//身份验证秘钥

    private String creat_time;//注册时间

    private String last_login_time;//最后登录时间

}
