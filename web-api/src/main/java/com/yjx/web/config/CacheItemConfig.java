package cn.com.webtax.app.config;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/9/26 14:50
 */
@Data
public class CacheItemConfig implements Serializable {

    /**
     * 缓存容器名称
     */
    private String name;
    /**
     * 缓存失效时间
     */
    private long expiryTimeSecond;
}
