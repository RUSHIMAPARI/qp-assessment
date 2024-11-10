package com.qp.assessment.gsms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qp.assessment.gsms.entity.Items;

public interface ItemsRepository extends PagingAndSortingRepository<Items, Long>, JpaSpecificationExecutor<Items> {

	Optional<Items> findByKeyAndIsActive(Long key, Character isActive);

	List<Items> findByKeyInAndIsActive(List<Long> itemIds, char c);

}