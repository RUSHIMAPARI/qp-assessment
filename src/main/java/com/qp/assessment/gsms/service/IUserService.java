package com.qp.assessment.gsms.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.qp.assessment.gsms.entity.User;
import com.qp.assessment.gsms.request.OrderRequest;
import com.qp.assessment.gsms.response.ItemsPageWrapperResponse;

public interface IUserService {
	
	public ItemsPageWrapperResponse findAllItemsFilteredPageable(Pageable pageable, Optional<String> filterText);

	public void placeOrder(@Valid OrderRequest orderRequest, User user);

}
