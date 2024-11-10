package com.qp.assessment.gsms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qp.assessment.gsms.entity.User;

public interface UsersRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

	public Optional<User> findByUserName(String userId);

	public Optional<User> findByUserNameAndIsActive(String userName, Character activeFlag);

	public Optional<User> findByKeyAndIsActive(Long key, Character activeFlag);


}