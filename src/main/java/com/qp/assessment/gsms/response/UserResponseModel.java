package com.qp.assessment.gsms.response;

import com.qp.assessment.gsms.entity.RoleTypes;
import com.qp.assessment.gsms.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel extends MessageResponse {
	private static final long serialVersionUID = -5173228930854399016L;

	private Long key;
	private String userName;
	private RoleTypes role;
	private String mobileNumber;
	private String emailId;
	private Character activeFlag;
	
	public static UserResponseModel init(User user) {
		return UserResponseModel.builder()
				.key(user.getKey())
				.userName(user.getUserName())
				.role(user.getRole())
				.mobileNumber(user.getMobileNumber())
				.emailId(user.getEmailId())
				.activeFlag(user.getIsActive())
				.status(ServiceStatus.SUCCESS)
				.build();
	}
}
