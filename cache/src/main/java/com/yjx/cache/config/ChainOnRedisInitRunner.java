package cn.com.webtax.cache.config;

import cn.com.webtax.cache.service.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import static cn.com.webtax.common.constants.RunnerOrderConstants.INIT_DATA_ON_REDIS_SORT;


/**
 * <p>
 * Redis数据初始化
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/13 14:17
 */
@Order(INIT_DATA_ON_REDIS_SORT)
@Slf4j
public class ChainOnRedisInitRunner implements CommandLineRunner {

    private RedisCacheService redisCacheService;

    ChainOnRedisInitRunner(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @Override
    public void run(String... args) {
        //加载积分规则
        redisCacheService.refreshIntegralRules(true);
        //加载字典列表
        redisCacheService.refreshDictTreeList(true);
        //加载地区列表
        redisCacheService.refreshRegions(false);
    }
}
