package com.yjx.web.context;

import com.yjx.common.context.SpringContextHolder;
import com.yjx.common.exception.ServiceException;
import com.yjx.common.util.HttpContextUtil;
import com.yjx.web.bean.WebLoginUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.yjx.common.constants.RequestConstants.HEADER_TOKEN;
import static com.yjx.common.exception.enums.CoreExceptionEnum.*;

/**
 * <p>
 * 登录用户信息上下文
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-06-25 17:04
 */
public class LoginContext {

    private LoginUserService loginUserService;

    public LoginContext(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    /**
     * 获取自身INSTANCE
     */
    public static LoginContext me() {
        return SpringContextHolder.getBean(LoginContext.class);
    }

    public LoginUserService getLoginUserService() {
        return loginUserService;
    }

    /**
     * 获取当前用户的token
     * <p>
     * 先判断header中是否有Authorization字段，
     * 如果header中没有这个字段，则抛出没有登录用户异常
     */
    public String getCurrentUserToken() {
        HttpServletRequest request = HttpContextUtil.getRequest();
        if (request == null) {
            //非http请求环境
            throw new ServiceException(SERVICE_ERROR);
        }

        //如果请求是在http环境下，则有request对象
        String authorization = request.getHeader(HEADER_TOKEN);
        if (!StringUtils.isEmpty(authorization)) {
            return authorization;
        }
        // 在导出场景下token包含在param中
        authorization = request.getParameter(HEADER_TOKEN);
        if (!StringUtils.isEmpty(authorization)) {
            return authorization;
        }
        throw new ServiceException(CURRENT_NOT_USER);
    }

    /**
     * 检查token是否合法,不合法则抛出异常
     *
     * @author Shawn Deng
     * @date 2018/10/24 17:15
     */
    public void checkToken() {
        String token = getCurrentUserToken();
        if (!loginUserService.checkToken(token)) {
            throw new ServiceException(TOKEN_ILLEGAL);
        }
    }

    /**
     * 获取当前用户
     * <p>
     * 先从ThreadLocal中拿user，如果有值就直接返回
     * </p>
     *
     * @return <T>
     * @author Shawn Deng
     * @date 2018/10/24 17:16
     */
    public WebLoginUser getLoginUser() {
        WebLoginUser currentUser = WebLoginUserHolder.get();
        return Optional.ofNullable(currentUser).orElseGet(() -> {
            String token = getCurrentUserToken();
            return loginUserService.getLoginUserByToken(token);
        });
    }
}
