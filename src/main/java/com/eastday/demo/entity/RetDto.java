package com.eastday.demo.entity;

import lombok.Data;

import java.util.Map;

/**
 * Created by admin on 2019/10/9.
 */
@Data
public class RetDto {
    private Boolean success;//登录成功与否标识
    private Integer message;//提示信息
    private Map<String,Object> content;//token

    public RetDto(Boolean success, Integer message, Map<String, Object> content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }
}
