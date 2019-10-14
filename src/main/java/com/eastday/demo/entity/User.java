package com.eastday.demo.entity;

public class User {

    private int uid;//用户id
    private String phone;//手机号
    private String nick_name;//昵称
    private String unionID;//微信唯一标示
    private String access_token;//身份验证秘钥
    private String creat_time;//注册时间
    private String last_login_time;//最后登录时间

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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUnionID() {
        return unionID;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public User(int uid, String phone, String nick_name, String unionID, String access_token, String creat_time, String last_login_time) {
        this.uid = uid;
        this.phone = phone;
        this.nick_name = nick_name;
        this.unionID = unionID;
        this.access_token = access_token;
        this.creat_time = creat_time;
        this.last_login_time = last_login_time;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", phone='" + phone + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", unionID='" + unionID + '\'' +
                ", access_token='" + access_token + '\'' +
                ", creat_time='" + creat_time + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                '}';
    }
}
