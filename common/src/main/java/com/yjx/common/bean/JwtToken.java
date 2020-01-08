package com.yjx.common.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/** 
 * <p> 
 * JWT 鉴权必要信息
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/3 17:02
 */ 
@Data
@Builder
public class JwtToken {

    /**
     * TOKEN
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireDate;
}
