package com.yjx.app.config;

import com.yjx.app.task.ChainOnOrderPayTaskRunner;
import com.yjx.app.task.IOrderAsyncService;
import com.yjx.common.config.VisibleThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 线程任务配置
 * </p>
 *
 * @author yejx
 * @date 2019-9-20 14:12
 */
@Configuration
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true)
@Slf4j
public class ThreadTaskConfig {

    private static final int THREAD_POOL_ORDER = 401;

    /**
     * 业务线程池
     *
     * @author yejx
     * @date 2019-9-20 15:10
     */
    @Bean
    @ConditionalOnMissingBean
    @Order(THREAD_POOL_ORDER)
    public Executor businessServiceExecutor() {
        log.info("1.开启业务队列业务线程池");
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        // 配置核心线程数 当前线程小于该值 直接创建线程
        executor.setCorePoolSize(8);
        // 配置最大线程数
        executor.setMaxPoolSize(20);
        // 配置队列大小
        executor.setQueueCapacity(1024);
        // 配置空闲线程的存活时间
        executor.setKeepAliveSeconds(300);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("business-handler-service-");

        // 线程池对拒绝任务的处理策略
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 执行初始化
        executor.initialize();
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(IOrderAsyncService.class)
    public ChainOnOrderPayTaskRunner chainOnOrderPayTaskRunner(IOrderAsyncService iOrderAsyncService) {
        return new ChainOnOrderPayTaskRunner(iOrderAsyncService);
    }

}
