package com.qp.assessment.gsms.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.exception.ResourceNotFoundException;
import com.qp.assessment.gsms.service.AuthService;
import com.qp.assessment.gsms.util.CommonConstants.ErrorConstants;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if(userName != null) {
			User user = authService.findByUserName(userName);
			return new AuthenticatedUser(user.getUserName(), user.getPassword(), 
					true, true, true, true, getGrantedAuthorities(user), user);
		}
		else {
			throw new ResourceNotFoundException(ErrorConstants.RESOURCE_FOR_NOT_FOUND, userName, "User");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if(user.getRole() != null) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" +user.getRole().toString()));
		}
		return grantedAuthorities;
	}
}
