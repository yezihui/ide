package com.yjx.web.context;

import com.yjx.web.bean.WebLoginUser;

/**
 * <p>
 * 当前登录用户的临时保存容器
 * <p>
 * 说明：
 * 当OPEN_UP_FLAG标识在ThreadLocal里为true
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/25 00:26
 */
public class WebLoginUserHolder {

    private static final ThreadLocal<Boolean> OPEN_UP_FLAG = new ThreadLocal<>();
    private static final ThreadLocal<WebLoginUser> LOGIN_USER_HOLDER = new ThreadLocal<>();

    /**
     * 初始化
     *
     * @author Shawn Deng
     * @date 2018/10/25 00:29
     */
    public static void init() {
        OPEN_UP_FLAG.set(true);
    }

    /**
     * 这个方法如果OPEN_UP_FLAG标识没开启，则会set失效
     *
     * @author Shawn Deng
     * @date 2018/10/25 00:29
     */
    public static void set(WebLoginUser webLoginUser) {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag != null && openUpFlag.equals(true)) {
            LOGIN_USER_HOLDER.set(webLoginUser);
        }
    }

    /**
     * 这个方法如果OPEN_UP_FLAG标识没开启，则会get值为null
     *
     * @author Shawn Deng
     * @date 2018/10/25 00:29
     */
    public static WebLoginUser get() {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag == null || openUpFlag.equals(false)) {
            return null;
        } else {
            return LOGIN_USER_HOLDER.get();
        }
    }

    /**
     * 删除保存的用户
     *
     * @author Shawn Deng
     * @date 2018/10/25 00:29
     */
    public static void remove() {
        OPEN_UP_FLAG.remove();
        LOGIN_USER_HOLDER.remove();
    }
}
