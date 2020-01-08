package com.yjx.web.modular.auth.service;

import com.yjx.common.bean.JwtToken;
import com.yjx.model.web.ro.LoginRo;

/**
 * <p>
 * 授权 服务类
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 17:01
 */
public interface IAuthService {

    /**
     * 登录认证
     *
     * @param loginRo 请求参数
     * @return JwtToken
     * @author yejx
     * @date 2019/12/3 17:01
     */
    JwtToken login(LoginRo loginRo);

    /**
     * 注销登录
     *
     * @author yejx
     * @date 2019/12/3 17:01
     */
    void logout();

}
