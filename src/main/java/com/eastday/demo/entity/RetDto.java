package com.eastday.demo.entity;

/**
 * Created by admin on 2019/10/9.
 */
public class RetDto {
    private Boolean success;//登录成功与否标识
    private String message;//提示信息
    private String access_token;//token

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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public RetDto(Boolean success, String message, String access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
    }

    public RetDto() {
    }
}
