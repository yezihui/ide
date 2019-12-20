package cn.com.webtax.cache.config;

import cn.com.webtax.cache.service.IDicService;
import cn.com.webtax.cache.service.IRegionService;
import cn.com.webtax.cache.service.RedisCacheService;
import cn.com.webtax.cache.service.impl.DicServiceImpl;
import cn.com.webtax.cache.service.impl.RedisCacheServiceImpl;
import cn.com.webtax.cache.service.impl.RegionServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 缓存自动配置
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/15 17:57
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
    public IDicService iDicService(){
        return new DicServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRegionService iRegionService(){
        return new RegionServiceImpl();
    }
}
