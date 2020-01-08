package com.yjx.web.config;

import cn.hutool.core.collection.CollUtil;
import com.yjx.cache.handler.FastJson2JsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>
 * 缓存初始化配置
 * </p>
 *
 * @author yejx
 * @date 2019/12/18 15:31
 */
@Configuration
@Slf4j
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer(Object.class);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
        log.info("========>Redis默认配置");
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(fastJson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        CacheItemConfig productCacheItemConfig = new CacheItemConfig();
//        productCacheItemConfig.setName(QA_ORDER_PAGE);
//        productCacheItemConfig.setExpiryTimeSecond(300);
//        List<CacheItemConfig> cacheItemConfigs = Lists.newArrayList(productCacheItemConfig);

        return new CustomizedRedisCacheManager(connectionFactory, CollUtil.newArrayList());
    }

}
