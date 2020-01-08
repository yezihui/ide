package com.yjx.web.config;

import com.yjx.web.context.LoginContext;
import com.yjx.web.context.LoginUserService;
import com.yjx.web.log.factory.LogManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

/**
 * <p>
 * 自定义快捷工具配置
 * </p>
 *
 * @author yejx
 * @date 2019/12/4 11:45
 */
@Configuration
public class ContextConfig {

    /**
     * 获取当前用户服务的便捷工具
     *
     * @param loginUserService 用户service
     * @return 登录用户上下文
     */
    @Bean
    public LoginContext loginContext(LoginUserService loginUserService) {
        return new LoginContext(loginUserService);
    }

    /**
     * 日志管理工具类
     *
     * @param executor 线程池
     * @return 工具管理bean
     */
    @Bean
    public LogManager logManager(@Qualifier("logTreadPoolTaskExecutor") Executor executor) {
        return new LogManager(executor);
    }
}
