package com.yjx.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 
 * <p> 
 *
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/5 17:07
 */ 
@Getter
@AllArgsConstructor
public enum CoreExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 
     */
    SERVICE_ERROR(500, "服务器异常"),
    PARAM_INVALID(501, "请求参数格式非法"),
    REQUEST_METHOD_ERROR(503, "请求方式不正确"),
    CURRENT_NOT_USER(201, "无访问权限"),
    TOKEN_ILLEGAL(202, "访问过期或无效,请重新授权");

    private Integer code;
    private String message;
}
