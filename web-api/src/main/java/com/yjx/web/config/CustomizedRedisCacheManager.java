package com.yjx.web.config;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** 
 * <p> 
 *  
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/26 15:18
 */ 
public class CustomizedRedisCacheManager extends RedisCacheManager {

    public CustomizedRedisCacheManager(RedisConnectionFactory connectionFactory, List<CacheItemConfig> cacheItemConfigList) {
        this(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory),
                RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer())).entryTtl(Duration.ofSeconds(300)),
                cacheItemConfigList
                        .stream()
                        .collect(Collectors.toMap(CacheItemConfig::getName, cacheItemConfig -> {
                            RedisCacheConfiguration cacheConfiguration =
                                    RedisCacheConfiguration
                                            .defaultCacheConfig()
                                            .disableCachingNullValues()
                                            .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                    .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                                            .entryTtl(Duration.ofSeconds(cacheItemConfig.getExpiryTimeSecond()))
                                            .prefixKeysWith(cacheItemConfig.getName());
                            return cacheConfiguration;
                        }))
        );

    }

    public CustomizedRedisCacheManager(
            RedisCacheWriter redisCacheWriter
            , RedisCacheConfiguration redisCacheConfiguration,
            Map<String, RedisCacheConfiguration> redisCacheConfigurationMap) {
        super(redisCacheWriter, redisCacheConfiguration, redisCacheConfigurationMap);
    }

}
