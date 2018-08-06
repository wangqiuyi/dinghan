/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octopus.affiliate.admin.annotation.LoginRequired;
import com.octopus.affiliate.admin.constant.CommonConstants;
import com.octopus.affiliate.admin.utils.ResponseUtils;
import com.octopus.affiliate.admin.utils.UserTokenUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginInterceptor
 *
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年2月28日 下午3:58:12
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final String LOGIN_TOKEN_KEY = "X-Token";

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            throw new IllegalArgumentException("LoginInterceptor only support HanlderMethod handler");
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        /**
         * 登陆账号验证
         */
        LoginRequired anno = null;
        if (handlerMethod.getMethod().isAnnotationPresent(LoginRequired.class)) {
            anno = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        } else if (handlerMethod.getBeanType().isAnnotationPresent(LoginRequired.class)) {
            anno = handlerMethod.getBeanType().getAnnotation(LoginRequired.class);
        }
        /**
         * 无登陆要求
         */
        if (null == anno) {
            return true;
        }
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        Integer userId = UserTokenUtils.getUserId(token);
        if (userId == null) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(OBJECT_MAPPER.writeValueAsString(ResponseUtils.fail401()));
            return false;
        } else {
            request.setAttribute(CommonConstants.LOGIN_USER_ID, userId);
            return true;
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }
}
