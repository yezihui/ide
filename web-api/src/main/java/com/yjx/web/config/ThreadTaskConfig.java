package cn.com.webtax.web.config;

import cn.com.webtax.common.config.VisibleThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 异步线程池支持配置
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/11/10 15:32
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadTaskConfig {

    /**
     * 操作日志线程池
     *
     * @return
     */
    @Bean("logTreadPoolTaskExecutor")
    public Executor logTreadPoolTaskExecutor() {
        log.info("日志异步线程池");
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置最大线程数
        executor.setMaxPoolSize(20);
        //配置队列大小
        executor.setQueueCapacity(1024);
        //配置空闲线程的存活时间
        executor.setKeepAliveSeconds(300);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("log-thread-pool-");

        // 线程池对拒绝任务的处理策略
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是有调用者所在的线程来执行
        // AbortPolicy：丢弃任务并抛出RejectedExecutionException异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        executor.initialize();
        return executor;
    }
}
