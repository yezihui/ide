package com.yjx.web.modular.auth.controller;

import com.yjx.common.bean.JwtToken;
import com.yjx.common.bean.ResponseData;
import com.yjx.model.web.ro.LoginRo;
import com.yjx.web.constants.AuthConstants;
import com.yjx.web.modular.auth.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/** 
 * <p> 
 *
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/3 10:46
 */ 
@Api(tags = "Auth-授权相关接口")
@RestController
public class AuthController {

    @Resource
    private IAuthService iAuthService;

    @PostMapping(AuthConstants.AUTH_LOGIN_URL)
    @ApiOperation(value = "登录接口", notes = "根据登录方式选择登录(登录成功可获取token)")
    public ResponseData<JwtToken> login(@RequestBody @Valid LoginRo param) {
        return ResponseData.success(iAuthService.login(param));
    }

    @PostMapping(AuthConstants.AUTH_LOGOUT_URL)
    @ApiOperation(value = "登出接口", notes = "注销token")
    public ResponseData logout() {
        return ResponseData.success();
    }

    @PostMapping(AuthConstants.AUTH_RESOURCES_URL)
    @ApiOperation(value = "获取权限资源", notes = "获取权限资源")
    public ResponseData resources() {
        return ResponseData.success();
    }
}
