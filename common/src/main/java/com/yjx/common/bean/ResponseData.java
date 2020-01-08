package com.yjx.common.bean;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 10:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResponseData<T> implements Serializable {

    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
    public static final String DEFAULT_ERROR_MESSAGE = "业务异常";
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_ERROR_CODE = 500;

    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public static ResponseData success() {
        return new ResponseData(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public static ResponseData success(Object data) {
        return new ResponseData(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static ResponseData success(Integer code, String message, Object data) {
        return new ResponseData(true, code, message, data);
    }

    public static ResponseData error() {
        return new ResponseData(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public static ResponseData error(Integer code, String message) {
        return new ResponseData(true, code, message, null);
    }

    public static ResponseData error(Integer code, String message, Object data) {
        return new ResponseData(true, code, message, data);
    }
}
