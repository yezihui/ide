package cn.com.webtax.web.enums;

/**
 * <p>
 * 日志类型
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-04-13 19:26
 */
public enum LogType {

    /**
     * 登录日志
     */
    LOGIN("登录日志"),

    /**
     * 登录失败日志
     */
    LOGIN_FAIL("登录失败日志"),

    /**
     * 获取用户权限失败
     */
    GET_USER_INFO_FAIL("获取用户权限失败"),

    /**
     * 注销登录
     */
    LOGOUT("注销登录"),

    /**
     * 异常日志
     */
    EXCEPTION("异常日志"),

    /**
     * 业务日志
     */
    BUSINESS("业务日志");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
