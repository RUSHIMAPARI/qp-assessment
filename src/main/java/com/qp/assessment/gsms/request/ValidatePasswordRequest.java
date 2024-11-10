package com.qp.assessment.gsms.request;

import javax.validation.constraints.NotBlank;

import com.qp.assessment.gsms.util.CommonConstants.LOGIN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePasswordRequest {

	private String userName;

	@NotBlank(message = LOGIN.VALIDATION.PASSWORD_REQUIRED)
	private String password;
}
