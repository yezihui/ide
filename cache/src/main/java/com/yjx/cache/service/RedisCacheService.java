package com.yjx.cache.service;

/**
 * <p>
 * Redis缓存服务接口类
 * </p>
 *
 * @author yejx
 * @date 2019/12/13 11:09
 */
public interface RedisCacheService {

    /**
     * 删除APP端TOKEN登录
     *
     * @author yejx
     * @date 2019/12/13 11:09
     */
    void deleteAppTokenDir();

    /**
     * 删除WEB端TOKEN登录
     *
     * @author yejx
     * @date 2019/12/13 11:09
     */
    void deleteWebTokenDir();


}
