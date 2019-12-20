package cn.com.webtax.common.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * JWT 鉴权必要信息
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/15 15:27
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
