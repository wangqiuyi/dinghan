/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin;

import com.octopus.affiliate.admin.web.interceptor.LoginInterceptor;
import com.octopus.affiliate.admin.web.resolver.UserMethodArgumentResolver;
import com.octopus.affiliate.model.Role;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * AppWebConfigurer
 * 
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年2月28日 下午4:06:23
 */
@Configuration
public class AppWebConfigurer extends WebMvcConfigurerAdapter {
    
    @Bean
    LoginInterceptor myLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    UserMethodArgumentResolver myUserMethodArgumentResolver() {
        return new UserMethodArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/public/**", "/import");
        super.addInterceptors(registry);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addArgumentResolvers(java.util.List)
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(myUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addFormatters(org.springframework.format.FormatterRegistry)
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MyCustomEnumConverter());
        super.addFormatters(registry);
    }
    
    public static class MyCustomEnumConverter implements Converter<String, Role> {
        @Override
        public Role convert(String code) {
           try {
               Role role = Role.valueOf(code);
               if (role != null) {
                   return role;
               }
               int intCode = NumberUtils.toInt(code, 0);
               return Role.fromCode(intCode);
           } catch(Exception e) {
              return null;
           }
        }
    }
}
