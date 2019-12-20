package cn.com.webtax.common.constants;

/**
 * <p>
 * 鉴权相关的常量
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/11/4 16:41
 */
public interface AuthConstants {

    /**
     * 登录鉴权地址
     */
    String AUTH_LOGIN_URL = "/api/auth/login";

    /**
     * 退出，注销鉴权地址
     */
    String AUTH_LOGOUT_URL = "/api/auth/logout";

    /**
     * 获取用户资源列表
     */
    String AUTH_RESOURCES_URL = "/api/auth/resources";

    /**
     * 修改安全密码
     */
    String AUTH_MODIFY_PASSWORD_URL = "/api/auth/modify/password";
}
