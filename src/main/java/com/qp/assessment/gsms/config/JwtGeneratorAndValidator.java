package com.qp.assessment.gsms.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGeneratorAndValidator extends JwtTokenUtil implements Serializable {
	
	private static final long serialVersionUID = -1285820316322029161L;

//	public String generateToken(AuthenticatedUser userDetails) {
//		Map<String, Object> claims = new HashMap<String, Object>();
//		String username = userDetails.getUser().getUserName();
//		
//		List<String> roles = userDetails.getAuthorities().stream()
//	            .map(GrantedAuthority::getAuthority)
//	            .collect(Collectors.toList());
//
//	    claims.put("roles", roles);
//		return doGenerateToken(claims, username, userDetails.getUser().getKey());
//	}
//	
//	public String doGenerateToken(Map<String, Object> claims, String subject, Long id) {
//		return Jwts.builder().setClaims(claims).setSubject(subject)
//					.setId(id.toString())
//					.setIssuedAt(new Date(System.currentTimeMillis()))
//					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//					.signWith(SignatureAlgorithm.HS512, secret).compact();
//	}

	public String generateToken(AuthenticatedUser userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    String username = userDetails.getUser().getUserName();

	    List<Map<String, String>> roles = userDetails.getAuthorities().stream()
	            .map(authority -> {
	                Map<String, String> role = new HashMap<>();
	                role.put("authority", authority.getAuthority());
	                return role;
	            })
	            .collect(Collectors.toList());

	    claims.put("roles", roles);
	    return doGenerateToken(claims, username, userDetails.getUser().getKey());
	}

	public String doGenerateToken(Map<String, Object> claims, String subject, Long id) {
	    return Jwts.builder()
	            .setClaims(claims)
	            .setSubject(subject)
	            .setId(id.toString())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))  // Set the expiration time
	            .signWith(SignatureAlgorithm.HS512, secret)
	            .compact();
	}

}
