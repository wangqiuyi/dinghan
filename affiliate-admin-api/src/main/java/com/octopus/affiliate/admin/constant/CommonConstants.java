/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin.constant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * AppConstants
 * 
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年6月21日 下午8:04:27
 */
public class CommonConstants {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        OBJECT_MAPPER.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        OBJECT_MAPPER.findAndRegisterModules();
    }

    public static final String TOKEN_KEY_PREFIX = "token_";

    public static final String LOGIN_USER_ID = "loginUserId";
}
