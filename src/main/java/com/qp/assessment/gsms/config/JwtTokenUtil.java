package com.qp.assessment.gsms.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final long JWT_TOKEN_VALIDITY = 30 * 60 * 1000;

	@Value("${app.jwtSecret}")
	protected String secret;

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (30 * 60 * 1000)))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public String getUseridFromToken(String token) {
		if (token.startsWith("Bearer ")) {
			token = token.replace("Bearer ", "");
		}
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public Long getExpiresInFromToken(String token) {
		return getExpirationDateFromToken(token).getTime();
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUseridFromToken(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	public List<String> getRolesFromJWT(String token) {
	    Claims claims = Jwts.parser()
	            .setSigningKey(secret)
	            .parseClaimsJws(token)
	            .getBody();
	    
	    return claims.get("roles", List.class);
	}

	public static void main(String[] args) {
		System.out.println(new JwtTokenUtil().generateToken("admin"));
	}
}
