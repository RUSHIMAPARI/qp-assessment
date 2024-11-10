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
public class ItemsPageWrapperResponse extends MessageResponse {
	private static final long serialVersionUID = -4591597441521464447L;
	
	private Page<ItemsResponseModel> page;

	public static ItemsPageWrapperResponse init(Page<ItemsResponseModel> page) {

		return ItemsPageWrapperResponse.builder()
				.page(page)
				.status(ServiceStatus.SUCCESS).build();
	}
}