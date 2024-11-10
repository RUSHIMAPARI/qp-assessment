package com.qp.assessment.gsms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qp.assessment.gsms.entity.OrderItems;

public interface OrderItemsRepository extends PagingAndSortingRepository<OrderItems, Long>, JpaSpecificationExecutor<OrderItems> {

	public Optional<OrderItems> findByKeyAndIsActive(Long key, Character activeFlag);


}