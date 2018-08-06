/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin.annotation;

import com.octopus.affiliate.model.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 登录注解
 * 
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年2月24日 下午6:04:29
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

    Role[] roles() default {};
}
