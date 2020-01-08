package com.yjx.web.modular.auth.service.impl;

import com.yjx.common.bean.JwtToken;
import com.yjx.model.web.ro.LoginRo;
import com.yjx.web.log.LogObjectHolder;
import com.yjx.web.modular.auth.service.IAuthService;
import com.yjx.web.util.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 17:02
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 登录认证
     *
     * @param loginRo 请求参数
     * @return JwtToken
     * @author yejx
     * @date 2019/12/3 17:01
     */
    @Override
    public JwtToken login(LoginRo loginRo) {
        LogObjectHolder.set(loginRo.getUsername());
        return JwtToken.builder().token(jwtTokenUtil.createJwtById(loginRo.getUsername(), null)).build();
    }

    /**
     * 注销登录
     *
     * @author yejx
     * @date 2019/12/3 17:01
     */
    @Override
    public void logout() {

    }
}
