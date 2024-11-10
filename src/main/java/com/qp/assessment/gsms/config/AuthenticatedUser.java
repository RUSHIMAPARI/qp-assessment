package com.qp.assessment.gsms.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.qp.assessment.gsms.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;

	private User user;
	
	public AuthenticatedUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, User user) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
	}

}
