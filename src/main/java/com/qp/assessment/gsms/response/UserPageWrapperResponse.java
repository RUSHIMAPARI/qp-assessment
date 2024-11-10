package com.qp.assessment.gsms.response;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserPageWrapperResponse extends MessageResponse {
	private static final long serialVersionUID = -4591597441521464447L;
	private Page<UserResponseModel> page;

	public static UserPageWrapperResponse init(Page<UserResponseModel> page) {

		return UserPageWrapperResponse.builder()
				.page(page)
				.status(ServiceStatus.SUCCESS).build();
	}
}