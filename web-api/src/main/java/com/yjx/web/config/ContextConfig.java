package cn.com.webtax.web.config;

import cn.com.webtax.web.context.LoginContext;
import cn.com.webtax.web.context.LoginUserService;
import cn.com.webtax.web.log.factory.LogManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

/**
 * <p>
 * 自定义快捷工具配置
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/25 22:36
 */
@Configuration
public class ContextConfig {

    /**
     * 获取当前用户服务的便捷工具
     *
     * @param loginUserService 登录用户服务
     * @return LoginContext
     * @author Shawn Deng
     * @date 2018/10/24 13:52
     */
    @Bean
    public LoginContext loginContext(LoginUserService loginUserService) {
        return new LoginContext(loginUserService);
    }

    @Bean
    public LogManager logManager(@Qualifier("logTreadPoolTaskExecutor") Executor executor) {
        return new LogManager(executor);
    }
}
