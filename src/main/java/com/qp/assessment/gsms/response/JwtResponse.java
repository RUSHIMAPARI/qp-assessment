package com.qp.assessment.gsms.response;

import com.qp.assessment.gsms.entity.RoleTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse extends MessageResponse{
	
	private static final long serialVersionUID = 299597347009341222L;
	
	private String accessToken;
	private Long expiresIn;
	private RoleTypes roles;
	private String userName;
	
	public static JwtResponse success(String accessToken, Long expiresIn, RoleTypes roles, String userName) {
		return JwtResponse.builder()
				.status(ServiceStatus.SUCCESS)
				.accessToken(accessToken)
				.expiresIn(expiresIn)
				.roles(roles)
				.userName(userName)
				.build();
	}
	
	public static JwtResponse success() {
		return JwtResponse.builder()
				.status(ServiceStatus.SUCCESS)
				.build();
	}

	public static JwtResponse error(String errorMessage) {
		return JwtResponse.builder()
				.status(ServiceStatus.FAILURE)
				.errorMessage(errorMessage)
				.build();
	}
}
