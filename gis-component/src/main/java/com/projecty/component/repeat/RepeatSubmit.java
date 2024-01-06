package com.projecty.component.repeat;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 陈宇锋
 * @date 2023/11/29
 * @description 接口防抖
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
    int Interval() default 1000;
}
