package cn.com.webtax.web.config;

import cn.com.webtax.cache.service.IAppUserInfoCacheService;
import cn.com.webtax.cache.service.impl.AppUserInfoCacheServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * <p>
 * 缓存初始化配置
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-05-13 20:33
 */
@Configuration
@Slf4j
public class CacheConfig {

    /**
     * 缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                //如果是空值，不缓存
                .disableCachingNullValues()
                .serializeValuesWith(pair);
        //设置默认超过期时间是1小时
        defaultCacheConfig.entryTtl(Duration.ofHours(1));
        //初始化RedisCacheManager
        return RedisCacheManager.builder(redisCacheWriter).cacheDefaults(defaultCacheConfig).build();
    }

    @Bean
    public IAppUserInfoCacheService iAppUserInfoCacheService(){
        return new AppUserInfoCacheServiceImpl();
    }

}
