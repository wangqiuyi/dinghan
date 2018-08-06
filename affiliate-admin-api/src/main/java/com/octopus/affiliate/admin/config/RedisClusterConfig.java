/*
 * Copyright (c) 2017 Wang Qiuyi. All Rights Reserved.
 */
package com.octopus.affiliate.admin.config;

import com.octopus.affiliate.admin.constant.CommonConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 * 
 * @author qiuyi.wang
 * @version qiuyi.wang Initial Created at 2017年2月7日 上午11:15:18
 */
@Configuration
public class RedisClusterConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(CommonConstants.OBJECT_MAPPER);
        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
