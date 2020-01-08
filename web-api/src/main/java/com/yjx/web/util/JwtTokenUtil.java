package com.yjx.web.util;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 17:24
 */
@Component("jwtTokenUtil")
public class JwtTokenUtil {
	
	@Value("${JwtTokenUtil.jwtKey}")
	private String jwtKey;

	public String createJwtById(String id, String subject) {
		return createJwtById(id, subject, DateUtil.offsetDay(DateUtil.date(), 1));
	}

	public String createJwtById(String id, String subject, Date tokenExpiration) {
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder()
	    		.setId(id)
				.setSubject(subject)
	    		.signWith(signatureAlgorithm, signingKey);
	    
	    if(null != tokenExpiration) {
	    	builder.setExpiration(tokenExpiration);
	    }
	 
	    return builder.compact();
	}

	private Claims getClaimFromToken(String token) {
		return (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtKey)).parseClaimsJws(token).getBody();
	}

	public String getJwtIdFromToken(String token) {
		return this.getClaimFromToken(token).getId();
	}

	public Date getExpirationDateFromToken(String token) {
		return this.getClaimFromToken(token).getExpiration();
	}

	public Boolean checkToken(String token) throws JwtException {
		try {
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtKey)).parseClaimsJws(token);
			return true;
		} catch (JwtException var3) {
			return false;
		}
	}

	public Boolean isTokenExpired(String token) {
		try {
			Date expiration = this.getExpirationDateFromToken(token);
			return expiration.before(new Date());
		} catch (ExpiredJwtException var3) {
			return true;
		}
	}
}
