package com.eastday.demo.entity;

import lombok.Data;

@Data
public class News {
    private String title;//标题
    private String stage;//期数
    private String time;//时间
    private String publisher;//签发
    private String editor;//作者
    private String auditor;//审核
    private String content;//内容

}
