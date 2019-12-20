package com.kimed.insuranceplatform.common.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("jwtTokenUtil")
public class JwtTokenUtil {
	
	@Value("${JwtTokenUtil.jwtKey}")
	private String jwtKey;
	
	//method to construct a JWT
	public String createJWT(Map<String,String> infoMap, Date tokenExpiration) {
	 
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    Map<String, Object> claims = new  HashMap<String, Object>(infoMap);
	    claims.put(Claims.ID,UUID.randomUUID());
	    
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder()
	    		.setClaims(claims)
	    		.signWith(signatureAlgorithm, signingKey);
	    
	    if(null != tokenExpiration) {
	    	builder.setExpiration(tokenExpiration);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	//method to validate and read the JWT
	public Map<String, String> parseJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(jwtKey))
	       .parseClaimsJws(jwt).getBody();
		
		Map<String,String> result = new HashMap<String, String>();
		//去除系统信息
		claims.remove(Claims.ISSUER);
		claims.remove(Claims.SUBJECT);
		claims.remove(Claims.AUDIENCE);
		claims.remove(Claims.EXPIRATION);
		claims.remove(Claims.NOT_BEFORE);
		claims.remove(Claims.ISSUED_AT);
		claims.remove(Claims.ID);
		
		for(Map.Entry<String, Object> en : claims.entrySet()) {
			result.put(en.getKey(), (String)en.getValue());
		}
		return result;
	}
}
