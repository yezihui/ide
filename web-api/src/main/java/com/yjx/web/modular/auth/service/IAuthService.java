package cn.com.webtax.web.modular.auth.service;

import cn.com.webtax.web.bean.AuthPwdDto;
import cn.com.webtax.web.bean.UserResource;
import cn.com.webtax.common.bean.JwtToken;
import cn.com.webtax.model.web.ro.LoginRo;

/**
 * <p>
 * 授权 服务类
 * </p>
 *
 * @author Shawn Deng
 * @since 2018-10-25
 */
public interface IAuthService {

    /**
     * 登录认证
     *
     * @param loginRo 请求参数
     * @return JwtToken
     * @author Shawn Deng
     * @date 2018/10/25 20:11
     */
    JwtToken login(LoginRo loginRo);

    /**
     * 注销登录
     *
     * @author Shawn Deng
     * @date 2018/10/25 20:11
     */
    void logout();

    /**
     * 获取用户权限资源
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/26 00:45
     */
    UserResource getUserResources();

    /**
     * 修改安全密码
     *
     * @param authPwdDto 请求参数
     * @author Shawn Deng
     * @date 2019-04-15 18:47
     */
    void modifyPassword(AuthPwdDto authPwdDto);
}
