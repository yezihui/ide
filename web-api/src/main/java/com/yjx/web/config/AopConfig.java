package cn.com.webtax.web.config;

import cn.com.webtax.web.aop.ChainOnLoginLogAop;
import cn.com.webtax.web.aop.ChainOnOperationLogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * AOP拦截日志配置,开发环境关闭日志写入提高开发效率
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/11/7 13:56
 */
@Configuration
public class AopConfig {

    @Bean
    public ChainOnLoginLogAop chainOnLoginLogAop() {
        return new ChainOnLoginLogAop();
    }

    @Bean
    public ChainOnOperationLogAop chainOnOperationLogAop() {
        return new ChainOnOperationLogAop();
    }
}
