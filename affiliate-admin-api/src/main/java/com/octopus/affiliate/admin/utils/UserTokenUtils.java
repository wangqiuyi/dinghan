package com.octopus.affiliate.admin.utils;

import com.octopus.affiliate.admin.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class UserTokenUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    public static Integer getUserId(String token) {

        if (StringUtils.isBlank(token)) {
            return null;
        }

        String tokenKey = CommonConstants.TOKEN_KEY_PREFIX + token;

        UserToken userToken = (UserToken) redisTemplate.boundValueOps(tokenKey).get();
        if (userToken == null) {
            return null;
        }

        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            redisTemplate.delete(tokenKey);
            return null;
        }

        return userToken.getUserId();
    }


    public static UserToken generateToken(Integer id) {
        UserToken userToken = null;


        String token = UUID.randomUUID().toString().replace("-", "");
        String tokenKey = CommonConstants.TOKEN_KEY_PREFIX + token;

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);

        userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);

        redisTemplate.boundValueOps(tokenKey).set(userToken, 24 * 3600, TimeUnit.MINUTES);

        return userToken;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static void main (String[] args) {
        System.out.println(UserTokenUtils.generateToken(1));
    }
}
