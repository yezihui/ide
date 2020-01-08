package com.yjx.web.config;

import com.yjx.web.aop.ChainOnLoginLogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * AOP拦截日志配置,开发环境关闭日志写入提高开发效率
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 17:42
 */
@Configuration
public class AopConfig {

    @Bean
    public ChainOnLoginLogAop chainOnLoginLogAop() {
        return new ChainOnLoginLogAop();
    }

//    @Bean
//    public ChainOnOperationLogAop chainOnOperationLogAop() {
//        return new ChainOnOperationLogAop();
//    }
}
