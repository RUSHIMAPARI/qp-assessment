package com.qp.assessment.gsms.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.util.CommonConstants.USER;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable, RequestPayload<User>{
	private static final long serialVersionUID = 191906027052762922L;
	
	@NotBlank(message = USER.VALIDATION.USER_NAME_REQUIRED)
	@Size(max = 255, message = USER.VALIDATION.USER_NAME_MAXLENGTH)
	private String userName;

	@NotBlank(message = USER.VALIDATION.FISRT_NAME_REQUIRED)
	@Size(max = 255, message = USER.VALIDATION.FIRST_NAME_MAXLENGTH)
	private String firstName;
	
	@NotBlank(message = USER.VALIDATION.LAST_NAME_REQUIRED)
	@Size(max = 255, message = USER.VALIDATION.LAST_NAME_MAXLENGTH)
	private String lastName;

	@NotEmpty(message = USER.VALIDATION.ROLES_REQUIRED)
	private String role;
	
	@NotBlank(message = USER.VALIDATION.MOBILENUMBER_REQUIRED)
	@Size(min = 10, max = 10, message = USER.VALIDATION.MOBILENUMBER_BETWEENLENGTH)
	private String mobileNumber;

	@NotBlank(message = USER.VALIDATION.EMAILID_REQUIRED)
	@Size(max = 255, message = USER.VALIDATION.EMAILID_MAXLENGTH)
	private String emailId;
	
	@Override
	public User mapToEntity() {
		User user = new User();
		BeanUtils.copyProperties(this, user);
		return user;
	}
	@Override
	public void updateEntity(User user) {
		BeanUtils.copyProperties(this, user);
	}
}

