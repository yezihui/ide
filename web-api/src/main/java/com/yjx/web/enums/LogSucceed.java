package cn.com.webtax.web.enums;

/**
 * <p>
 * 业务是否成功的日志记录
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-04-13 19:27
 */
public enum LogSucceed {

    /**
     * 成功
     */
    SUCCESS("成功"),

    /**
     * 失败
     */
    FAIL("失败");

    String message;

    LogSucceed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
