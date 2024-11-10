package com.qp.assessment.gsms.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthorizationFilter extends BasicAuthenticationFilter{

	@Value("${app.jwtSecret}")
	private String secret;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info(request.getRequestURI());
		if(request.getRequestURI().contains("/open/") || request.getRequestURI().contains("/actuator/")) {
			chain.doFilter(request, response);
		}
		else {
			final String authHeader = request.getHeader("Authorization");
			System.out.println("authHeader -"+authHeader);
			String token = "";
			if(authHeader != null) {
				token = authHeader.replace("Bearer ", "");
				try {
					UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					chain.doFilter(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					return;
				}
			}
			else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return;
			}
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		List<SimpleGrantedAuthority> authorities = getRoles(token);
		String userId = getUserId(token);
		if(userId != null && authorities != null) {
			return new UsernamePasswordAuthenticationToken(userId, null, authorities);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SimpleGrantedAuthority> getRoles(String token) {
	    List<Map<String, String>> roles = (List<Map<String, String>>) Jwts.parser()
								            .setSigningKey(secret)
								            .parseClaimsJws(token)
								            .getBody()
								            .get("roles", ArrayList.class);

	    return roles.stream()
	            .map(mp -> new SimpleGrantedAuthority(mp.get("authority"))) // Access the "authority" key
	            .collect(Collectors.toList());
	}

	public String getUserId(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getId();
	}
}
