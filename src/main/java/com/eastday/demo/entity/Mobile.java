package com.eastday.demo.entity;

public class Mobile {

    private String phone;//手机号
    private String code;//短信验证码
    private long dead_line;//验证码失效时间
    private int usable;//是否使用（0：未使用 1：已使用）

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getDead_line() {
        return dead_line;
    }

    public void setDead_line(long dead_line) {
        this.dead_line = dead_line;
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }

    public Mobile() {
    }

    public Mobile(String phone, String code, long dead_line, int usable) {
        this.phone = phone;
        this.code = code;
        this.dead_line = dead_line;
        this.usable = usable;
    }
}
