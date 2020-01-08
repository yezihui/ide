package com.yjx.cache.config;

import com.yjx.cache.service.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;


/**
 * <p>
 * Redis数据初始化
 * </p>
 *
 * @author yejx
 * @date 2019-12-13 11:18
 */
@Order(300)
@Slf4j
public class ChainOnRedisInitRunner implements CommandLineRunner {

    private RedisCacheService redisCacheService;

    ChainOnRedisInitRunner(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @Override
    public void run(String... args) {
        //加载积分规则
        //加载字典列表
        //加载地区列表
    }
}
