package com.eastday.demo.entity;

import lombok.Data;

/**
 * Created by admin on 2019/10/9.
 */
@Data
public class RetDto {
    private Boolean success;//登录成功与否标识
    private Integer message;//提示信息
    private String access_token;//token

    public RetDto(Boolean success, Integer message, String access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
    }
}
