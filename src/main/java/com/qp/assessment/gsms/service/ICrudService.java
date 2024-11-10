package com.qp.assessment.gsms.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.qp.assessment.gsms.request.AbstractRequest;
import com.qp.assessment.gsms.response.AbstractResponse;

public interface ICrudService<T, ID> {

	void save(T t);
	
	void update(T t);
	
	Optional<T> findByKey(Long id);
	
	Page<AbstractResponse> findAllFilteredPageable(Pageable pageable, Optional<AbstractRequest> filterText, Long userId);

}
