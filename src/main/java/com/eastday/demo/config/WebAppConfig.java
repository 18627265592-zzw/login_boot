package com.eastday.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 新建Token拦截器
 * @Title: InterceptorConfig.java
 * @author zzw
 * @date 2019年10月31日 下午20:39:28
 * @version V1.0
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }
    @Bean
    public InterceptorConfig authenticationInterceptor() {
        return new InterceptorConfig();// 自己写的拦截器
    }




}
