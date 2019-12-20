package cn.com.webtax.web.log.factory;

import cn.com.webtax.common.context.SpringContextHolder;

import java.util.TimerTask;
import java.util.concurrent.Executor;

/**
 * <p>
 * 日志管理器
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/11/7 14:08
 */
public class LogManager {

    private Executor executor;

    public LogManager(Executor executor) {
        this.executor = executor;
    }

    public static LogManager me() {
        return SpringContextHolder.getBean(LogManager.class);
    }

    public void execute(TimerTask task){
        executor.execute(task);
    }
}
