package com.eastday.demo.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Mobile {

    @Id
    @KeySql(useGeneratedKeys = true)
    private String mid;

    @Column(name="uid")
    private Integer uid;

    @Column(name="mobile_phone")
    private String mobilePhone;//手机号

    @Column(name="mobile_code")
    private String mobileCode;//短信验证码

    @Column(name="mobile_send_time")
    private Date mobileSendTime;//发送时间

    @Column(name="mobile_usable")
    private Integer mobileUsable;//是否使用（0：未使用 1：已使用）

}
