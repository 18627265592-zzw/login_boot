package com.eastday.demo.entity;

import lombok.Data;

@Data
public class Mobile {

    private String phone;//手机号
    private String code;//短信验证码
    private long dead_line;//验证码失效时间
    private int usable;//是否使用（0：未使用 1：已使用）

}
