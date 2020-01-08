package com.yjx.web.log.factory;

import com.yjx.mongo.document.system.LoginLog;
import com.yjx.mongo.document.system.OperationLog;
import com.yjx.mongo.modular.system.repository.LoginLogRepository;
import com.yjx.mongo.modular.system.repository.OperationLogRepository;
import com.yjx.common.context.SpringContextHolder;
import com.yjx.web.enums.LogSucceed;
import com.yjx.web.enums.LogType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TimerTask;

/** 
 * <p> 
 * 日志操作任务创建工厂 
 * </p> 
 *
 * @author yejx 
 * @date 2019-12-03 16:51
 */ 
@Slf4j
public class LogTaskFactory {

    private static LoginLogRepository loginLogRepository = SpringContextHolder.getBean(LoginLogRepository.class);

    private static OperationLogRepository operationLogRepository = SpringContextHolder.getBean(OperationLogRepository.class);

    /**
     * 创建登录日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask loginSuccessLog(String requestNo, String ipAddress, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, requestNo, LogSucceed.SUCCESS, ipAddress, message);
                try {
                    loginLogRepository.save(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 创建登录失败日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask loginFailLog(String requestNo, String ipAddress, String message) {

        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN_FAIL, requestNo, LogSucceed.FAIL, ipAddress, message);
                try {
                    loginLogRepository.save(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 创建获取用户权限失败资源日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask resourceLog(String requestNo, String ipAddress, String message) {

        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.GET_USER_INFO_FAIL, requestNo, LogSucceed.FAIL, ipAddress, message);
                try {
                    loginLogRepository.save(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 创建注销登录日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask logoutSuccessLog(String requestNo, String ipAddress, String message) {

        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGOUT, requestNo, LogSucceed.SUCCESS, ipAddress, message);
                try {
                    loginLogRepository.save(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 创建注销登录失败日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask logoutFailLog(String requestNo, String ipAddress, String message) {

        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGOUT, requestNo, LogSucceed.FAIL, ipAddress, message);
                try {
                    loginLogRepository.save(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 创建业务操作日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask businessLog(String businessName, String requestNo, String operationName, String className, String method,
                                        String requestUrl, String requestParams, String message,
                                        String username, String operationIp) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog sysLog = LogFactory.createOperationLog(LogType.BUSINESS, businessName,
                        requestNo, operationName, className,
                        method, requestUrl, requestParams, LogSucceed.SUCCESS,
                        message, username, operationIp);
                try {
                    operationLogRepository.insert(sysLog);
                } catch (Exception e) {
                    log.error("创建操作日志异常!", e);
                }
            }
        };
    }

    /**
     * 创建业务操作异常日志
     *
     * @return TimerTask
     * @author yejx
     * @date 2019-12-03 16:51
     */
    public static TimerTask exceptionLog(String businessName, String requestNo, String operationName, String className, String method,
                                         String requestUrl, String requestParams,
                                         String username, String operationIp, final Throwable exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = getExceptionMsg(exception);
                OperationLog sysLog = LogFactory.createOperationLog(LogType.EXCEPTION, businessName,
                        requestNo, operationName, className,
                        method, requestUrl, requestParams, LogSucceed.FAIL,
                        msg, username, operationIp);
                try {
                    operationLogRepository.insert(sysLog);
                } catch (Exception e) {
                    log.error("创建操作日志异常!", e);
                }
            }
        };
    }

    /**
     * 获取异常的具体信息
     *
     * @param e 异常
     * @return 信息
     * @author yejx
     * @date 2019-12-03 16:51
     */
    private static String getExceptionMsg(Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }
}
