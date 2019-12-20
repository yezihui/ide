package cn.com.webtax.web.log.factory;

import cn.com.webtax.mongo.document.modular.system.LoginLog;
import cn.com.webtax.mongo.document.modular.system.OperationLog;
import cn.com.webtax.web.enums.LogSucceed;
import cn.com.webtax.web.enums.LogType;
import cn.hutool.core.date.DateUtil;

/**
 * <p>
 * 日志构建工厂
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/11/7 14:06
 */
public class LogFactory {

    /**
     * 创建登录日志工厂
     *
     * @param logType   日志类型
     * @param requestNo 请求号
     * @param succeed   是否成功
     * @param ipAddress 登录IP地址
     * @param message   备注信息
     * @return LoginLog
     */
    public static LoginLog createLoginLog(LogType logType, String requestNo, LogSucceed succeed, String ipAddress, String message) {
        return LoginLog.builder()
                .logName(logType.getMessage())
                .requestNo(requestNo)
                .succeed(succeed.getMessage())
                .message(message)
                .ipAddress(ipAddress)
                .createTime(DateUtil.current(false))
                .build();
    }

    /**
     * 创建操作日志工厂
     *
     * @param logType       日志类型
     * @param logName       日志名称
     * @param requestNo     请求号
     * @param operationName 操作名称
     * @param className     类名
     * @param method        方法名
     * @param requestUrl    请求地址
     * @param requestParams 请求参数
     * @param succeed       是否成功
     * @param message       备注信息
     * @param username      操作用户
     * @param operationIp   操作客户端IP地址
     * @return SysOperationLogEntity
     */
    public static OperationLog createOperationLog(LogType logType, String logName, String requestNo,
                                                  String operationName, String className,
                                                  String method, String requestUrl, String requestParams,
                                                  LogSucceed succeed, String message,
                                                  String username, String operationIp) {
        return OperationLog.builder()
                .logType(logType.getMessage())
                .logName(logName)
                .requestNo(requestNo)
                .operationName(operationName)
                .className(className)
                .method(method)
                .requestUrl(requestUrl)
                .requestParams(requestParams)
                .succeed(succeed.getMessage())
                .message(message)
                .username(username)
                .operationIp(operationIp)
                .createTime(DateUtil.current(false))
                .build();
    }
}
