package com.eastday.demo.config;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface Authentication {
    String value() default "";
}
