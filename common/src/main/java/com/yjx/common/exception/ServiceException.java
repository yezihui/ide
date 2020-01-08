package com.yjx.common.exception;

import com.yjx.common.exception.enums.AbstractBaseExceptionEnum;
import lombok.Data;

/** 
 * <p> 
 *  
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/5 17:07
 */ 
@Data
public class ServiceException extends RuntimeException {
    
    private Integer code;
    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }
}
