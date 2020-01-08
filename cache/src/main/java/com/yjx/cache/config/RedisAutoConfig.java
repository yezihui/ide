package com.yjx.cache.config;

import com.yjx.cache.service.IDictService;
import com.yjx.cache.service.RedisCacheService;
import com.yjx.cache.service.impl.DictServiceImpl;
import com.yjx.cache.service.impl.RedisCacheServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 缓存自动配置
 * </p>
 *
 * @author yejx
 * @date 2019-12-13 11:18
 */
@Configuration
public class RedisAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public RedisCacheService redisCacheService() {
        return new RedisCacheServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IDictService iDictService() {
        return new DictServiceImpl();
    }

}
