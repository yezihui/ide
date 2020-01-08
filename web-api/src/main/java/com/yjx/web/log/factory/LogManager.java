package com.yjx.web.log.factory;

import com.yjx.common.context.SpringContextHolder;

import java.util.TimerTask;
import java.util.concurrent.Executor;

/**
 * <p>
 * 日志管理器
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 16:51
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
