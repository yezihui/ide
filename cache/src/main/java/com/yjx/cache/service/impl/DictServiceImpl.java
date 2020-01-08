package com.yjx.cache.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.yjx.cache.constants.RedisDirConstants;
import com.yjx.cache.service.IDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/12/18 15:15
 */
@Slf4j
public class DictServiceImpl implements IDictService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<String> dictAll() {
        log.info("加载缓存数据（字典列表）");
        BoundListOperations opts = redisTemplate.boundListOps(RedisDirConstants.DICTIONARY_TREE_KEY);
        if (ObjectUtil.isNotNull(opts) && opts.size() > 0) {
            return opts.range(0, -1);
        }
        List<String> dictList = Arrays.asList("111","2222","3333");
        opts.leftPushAll(dictList.toArray());
        opts.expire(86400, TimeUnit.SECONDS);
        return opts.range(0, -1);
    }
}
