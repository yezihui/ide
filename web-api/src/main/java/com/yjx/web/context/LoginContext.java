package cn.com.webtax.web.context;

import cn.com.webtax.common.context.SpringContextHolder;
import cn.com.webtax.web.api.ILoginContext;
import cn.com.webtax.web.api.WebLoginUserHolder;
import cn.com.webtax.web.bean.WebLoginUser;
import com.shawn.cloud.springboot.common.exception.ServiceException;
import com.shawn.cloud.springboot.core.util.HttpContextUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static cn.com.webtax.common.constants.RequestConstants.HEADER_TOKEN;
import static com.shawn.cloud.springboot.core.exception.enums.CoreExceptionEnum.*;

/**
 * <p>
 * 登录用户信息上下文
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-06-25 17:04
 */
public class LoginContext implements ILoginContext {

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
    @Override
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
    @Override
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
    @Override
    public WebLoginUser getLoginUser() {
        WebLoginUser currentUser = WebLoginUserHolder.get();
        return Optional.ofNullable(currentUser).orElseGet(() -> {
            String token = getCurrentUserToken();
            return loginUserService.getLoginUserByToken(token);
        });
    }
}
