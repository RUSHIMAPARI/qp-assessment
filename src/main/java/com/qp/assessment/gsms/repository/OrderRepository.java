package com.qp.assessment.gsms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qp.assessment.gsms.entity.Orders;


public interface OrderRepository extends PagingAndSortingRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {

	public Optional<Orders> findByKeyAndIsActive(Long key, Character activeFlag);

}