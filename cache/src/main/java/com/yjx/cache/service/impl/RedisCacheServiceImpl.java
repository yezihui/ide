package com.yjx.cache.service.impl;

import com.yjx.cache.constants.RedisDirConstants;
import com.yjx.cache.service.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/** 
 * <p> 
 * Redis缓存服务接口实现类
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/13 11:17
 */ 
@Slf4j
public class RedisCacheServiceImpl implements RedisCacheService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void deleteAppTokenDir() {
        log.info("删除APP端登录TOKEN");
        Set<String> keys = redisTemplate.keys(RedisDirConstants.APP_LOGIN_USER_TOKEN_PREFIX + "*");
        redisTemplate.delete(Optional.ofNullable(keys).orElse(Collections.emptySet()));
    }

    @Override
    public void deleteWebTokenDir() {
        log.info("删除web端登录TOKEN");
        Set<String> keys = redisTemplate.keys(RedisDirConstants.WEB_LOGIN_USER_TOKEN_PREFIX + "*");
        redisTemplate.delete(Optional.ofNullable(keys).orElse(Collections.emptySet()));
    }
}
