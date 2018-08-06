/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin.web.resolver;

import com.octopus.affiliate.admin.constant.CommonConstants;
import com.octopus.affiliate.admin.dao.UserDAO;
import com.octopus.affiliate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * UserMethodArgumentResolver
 *
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年5月10日 下午3:38:35
 */
@Component
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserDAO userDAO;

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return User.class.isAssignableFrom(parameter.getParameterType());
    }

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (User.class.isAssignableFrom(parameter.getParameterType())) {
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            if (request == null) {
                return null;
            }

            Integer loginUserId = (Integer) request.getAttribute(CommonConstants.LOGIN_USER_ID);
            if (loginUserId == null) {
                return null;
            }
            User user = userDAO.getUserById(loginUserId);
            return user;
        }
        return null;
    }
}
