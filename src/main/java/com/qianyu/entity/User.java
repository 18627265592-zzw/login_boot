package com.qianyu.entity;

public class User {


    private int uid;//用户id
    private String phone;//手机号
    private String nickName;//昵称
    private String openId;//微信登录唯一标示
    private String code;//短信验证码
    private long dead_line;//验证码失效时间
    private int usable;//是否使用（0：未使用 1：已使用）
    private String createTime;//注册时间
    private String lastLoginTime;//最后登录时间


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public User(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }

    public User() {
    }

    public User(int uid, String phone, String nickName, String openId, String code, long dead_line, int usable, String createTime, String lastLoginTime) {
        this.uid = uid;
        this.phone = phone;
        this.nickName = nickName;
        this.openId = openId;
        this.code = code;
        this.dead_line = dead_line;
        this.usable = usable;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
    }
}
