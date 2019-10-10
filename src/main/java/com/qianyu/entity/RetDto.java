package com.qianyu.entity;

/**
 * Created by admin on 2019/10/9.
 */
public class RetDto {
    private Boolean success;//登录成功与否标识
    private String message;//提示信息
    private String token;//token

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RetDto(Boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }

    public RetDto() {
    }
}
