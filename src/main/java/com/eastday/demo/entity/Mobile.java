package com.eastday.demo.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Mobile {

    @Id
    @KeySql(useGeneratedKeys = true)
    private String pid;

    private String phone;//手机号

    private String code;//短信验证码

    private String send_time;//发送时间

    private Long send_line;//发送时间戳

    private Integer usable;//是否使用（0：未使用 1：已使用）

}
