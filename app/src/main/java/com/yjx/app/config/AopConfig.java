package com.yjx.app.config;

import com.yjx.app.aop.ChainOnRequestHeaderAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * aop Bean配置
 * </p>
 *
 * @author yejx
 * @date 2019/9/18 16:21
 */
@Configuration
public class AopConfig {

    @Bean
    public ChainOnRequestHeaderAop chainOnRequestHeaderAop() {
        return new ChainOnRequestHeaderAop();
    }
}
